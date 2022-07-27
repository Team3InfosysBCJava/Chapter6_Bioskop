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

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", email_id='" + email_id + '\'' +
                '}';
    }
}
