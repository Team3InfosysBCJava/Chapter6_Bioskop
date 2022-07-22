package com.TeamC.Chapter6.Model;


import com.TeamC.Chapter6.DTO.UserResponseDTO;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;


    @Column(name = "username")
    private String userName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String role;

    public UserResponseDTO convertToResponse(){
        return UserResponseDTO.builder()
                .user_id(this.userId)
                .user_name(this.userName)
                .email_id(this.emailId)
                .pass_word(this.password)
                .build();
    }









    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + userName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}