//package com.TeamC.Chapter6.Security;
//
//import com.TeamC.Chapter6.Model.Filter.CustomAuthenticationFilter;
//import com.TeamC.Chapter6.Model.Filter.CustomAuthorizationFilter;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.http.HttpMethod.*;
//
//@Configuration
//@AllArgsConstructor
//public class SecurityConfig {
//
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Bean
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
////        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
////        customAuthenticationFilter.setFilterProcessesUrl("/login");
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/login/**","/sign-up/**", "/token/refresh/**","/swagger-ui/**").permitAll();
//        http.authorizeRequests()
//                .antMatchers("/team3/**").hasAnyRole("ADMIN","CUSTOMER","USER")
//                .antMatchers(POST,"/films/create-film").hasRole("ADMIN") //ADMIN
//                .antMatchers(PUT,"/films/update/{filmId}").hasRole("ADMIN") //ADMIN
//                .antMatchers(DELETE,"/films/delete/{filmId}").hasRole("ADMIN") //ADMIN
//                .antMatchers(PUT,"/users/update/{users_Id}").hasAnyRole("CUSTOMER","ADMIN")
//                .antMatchers(DELETE,"/users/delete/{users_Id}").hasAnyRole("CUSTOMER","ADMIN")
//                .antMatchers(GET,"/reports/print/reservations/:userName").hasAnyRole("CUSTOMER","ADMIN");
//                //ALL VISITOR ACCESS
////        http.addFilter(customAuthenticationFilter);
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return (web) -> web.ignoring().antMatchers("/v2/api-docs",
//                "/configuration/ui",
//                "/swagger-resources/**",
//                "/configuration/security",
//                "/swagger-ui.html",
//                "/webjars/**");
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
//        return authConfig.getAuthenticationManager();
//    }
//
//}
