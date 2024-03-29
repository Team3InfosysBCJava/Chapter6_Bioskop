package com.TeamC.Chapter6.Repository;


import com.TeamC.Chapter6.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

    @Query(value = "select * from users u where is_available =?1", nativeQuery = true)
    public List<User> getUserByIsPlaying(String name);

    @Query("SELECT u FROM User u WHERE CONCAT(u.userId, ' ', u.userName, ' ', u.emailId, ' ') LIKE %?1%")
    public List<User> search(String keyword);

    @Query("Select u from User u where u.userName like %:name% ORDER BY u.userId ASC")
    public Page<User> searchByname(@Param("name")String name, Pageable pageable);

    Optional<User> findByUserName(String userName);

}
