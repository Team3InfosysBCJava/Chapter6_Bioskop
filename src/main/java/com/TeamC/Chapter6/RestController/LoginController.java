package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.UserResponseDTO;
import com.TeamC.Chapter6.Helper.ResourceNotFoundException;
import com.TeamC.Chapter6.Model.User;
import com.TeamC.Chapter6.Repository.UserRepository;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Security.MyUserDetails;
import com.TeamC.Chapter6.Service.UserServiceImplements;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Tag(name = "1. Login Controller")
@AllArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Team C Bioskop Rest API - Docs"))
    public class LoginController {

    private UserServiceImplements userServiceImplements;
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Operation(summary = "Input username - password for get token")
    @PostMapping("/login")
    public void getToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Object> createUser(@RequestBody User user) throws Exception {
        try {
            User userResult = userServiceImplements.createUser(user);
            if(user.getRole().equals("ROLE_ADMIN")){
                throw new ResourceNotFoundException("role only customer or user");
            }
            UserResponseDTO userget = userResult.convertToResponse();
            Map<String, Object> userMap = new HashMap<>();
            List<Map<String, Object>> maps = new ArrayList<>();

            logger.info("==================== Logger Start Create Users ====================");
            logger.info("User Successfully Created !");
            logger.info("ID       : " + userResult.getUserId());
            logger.info("Username : " + userResult.getUserName());
            logger.info("Email    : " + userResult.getEmailId());
            logger.info("Password : " + userResult.getPassword());
            logger.info("Roles    : " + userResult.getRole());

            userMap.put("ID             ", userResult.getUserId());
            userMap.put("Username       ", userResult.getUserName());
            userMap.put("Email          ", userResult.getEmailId());
            userMap.put("Password       ", userResult.getPassword());
            userMap.put("Roles          ",userResult.getRole());
            maps.add(userMap);
            logger.info("==================== Logger End Create Users   ====================");
            logger.info(" ");
            return ResponseHandler.generateResponse("Successfully Created User!", HttpStatus.CREATED, userget);
        } catch (Exception e) {
            logger.info("==================== Logger Start Create Users     ====================");
            logger.error(String.valueOf(ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST,"User Already Exist!")));
            logger.info("==================== Logger End Create Users     ====================");
            logger.info(" ");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "User Already Exist!");
        }
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userServiceImplements.getUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRole())
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
