package com.TeamC.Chapter6.DTO;

import com.TeamC.Chapter6.Model.Film;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilmRequestDTO {

    private String filmTitle;

    private Boolean status;

    public Film convertToEntity(){

        return Film.builder().filmName(this.filmTitle).isPlaying(this.status).build();
    }

}
