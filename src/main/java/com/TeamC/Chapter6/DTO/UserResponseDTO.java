package com.TeamC.Chapter6.DTO;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private Long user_id;



    private String user_name;

    private String email_id;

    private String pass_word;

    @Override
    public String toString() {
        return "\n ScheduleResponseDTO{" +
                "userID=" + user_id +
                ", email=" + email_id +
                ", password=" + pass_word +
                ", username=" + user_name +
                '}';
    }


}
