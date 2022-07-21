package com.TeamC.Chapter6.Security;

import com.TeamC.Chapter6.Model.User;
import com.TeamC.Chapter6.Repository.UserRepository;
import com.TeamC.Chapter6.Service.UserServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + userName));
        return new MyUserDetails(user);
    }
}
