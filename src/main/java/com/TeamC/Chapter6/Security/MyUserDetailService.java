package com.TeamC.Chapter6.Security;

import com.TeamC.Chapter6.Model.User;
import com.TeamC.Chapter6.Service.UserServiceImplements;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailService implements UserDetailsService {
    UserServiceImplements userServiceImplements;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userServiceImplements.getUserById(email); //*

        return null;
    }
}


//BELUM ADA METHOD DIMANA DIPAKAI UNTUK GET ONE BY USERNAME
//BELOM ADA KOLOM USERNAME DI DATABASE
//BELOM ADA KOLOM ROLES DI DATABASE