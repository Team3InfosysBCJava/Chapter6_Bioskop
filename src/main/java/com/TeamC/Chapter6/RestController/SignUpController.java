package com.TeamC.Chapter6.RestController;


import com.TeamC.Chapter6.DTO.UserResponseDTO;
import com.TeamC.Chapter6.Model.User;
import com.TeamC.Chapter6.Repository.UserRepository;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Service.UserServiceImplements;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.TeamC.Chapter6.Helper.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@Tag(name = "2. Sign Up Controller")
public class SignUpController {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    private UserServiceImplements userServiceImplements;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    /***
     * Create User, Logger and Response DONE
     * @param user
     * @return
     */
    @PostMapping("/sign-up")
    public ResponseEntity <Object> createUser(@RequestBody User user) {
        try {
            User userResult = userServiceImplements.createUser(user);
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
}
