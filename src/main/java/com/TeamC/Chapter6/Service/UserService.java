package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> getAll();
    User createUser(User user);
    Optional<User> getUserById(Long users_Id);
    void deleteUserById(Long users_Id);
    User updateUser(User user) throws Exception;
    User getReferenceById(Long Id);
    List<User> search(String keyword);

    List<User> getUserByIsPlaying(String name);

    Page<User> search(String keyword, Integer pageNumber);
    User getUserByUsername(String username);
}
