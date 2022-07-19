package com.TeamC.Chapter6.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilmResponseDTO {

    private Long filmId;

    private String filmTitle;

    private Boolean status;

    public String toString(){
        return "filmResponseDTO{" +
                "filmId=" + filmTitle +
                ", title='" + filmTitle + '\'' +
                ", status=" + status +
                '}';
    }
}
