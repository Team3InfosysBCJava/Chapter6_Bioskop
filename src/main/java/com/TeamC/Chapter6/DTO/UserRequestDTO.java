package com.TeamC.Chapter6.DTO;


import com.TeamC.Chapter6.Model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private Long user_id;

    private String user_name;

    private String email_id;

    private String pass_word;


    public User convertToEntity(){
        return User.builder().userId(this.user_id).userName(this.user_name).emailId(this.email_id)
                .password(this.pass_word).build();


    }


}