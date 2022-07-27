package com.TeamC.Chapter6.Security;

import com.TeamC.Chapter6.Model.Filter.CustomAuthenticationFilter;
import com.TeamC.Chapter6.Model.Filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login/**","/sign-up/**", "/token/refresh/**","/swagger-ui/**").permitAll();
        http.authorizeRequests()
                //ADMIN
                .antMatchers("/team3/dashboard/**").hasAnyRole("ADMIN","CUSTOMER","USER")

                .antMatchers(POST,"/team3/v2/films/create-film").hasRole("ADMIN")
                .antMatchers(PUT,"/films/update/{filmId}").hasRole("ADMIN")
                .antMatchers(DELETE,"/films/delete/{filmId}").hasRole("ADMIN")
                .antMatchers("/dashboard/**").hasRole("ADMIN") //POST, PUT, DELETE
                //sign-up
                //CUSTOMER
                .antMatchers(PUT,"/users/update/").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(GET,"/users").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(GET,"/users/{id}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(DELETE,"/users/delete/").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers(GET,"/print/reservations/byuserName").hasAnyRole("CUSTOMER","ADMIN")

                //ALL VISITOR ACCESS
                .antMatchers("/**").permitAll();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}

