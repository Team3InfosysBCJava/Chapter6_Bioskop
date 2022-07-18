package com.TeamC.Chapter6.Model;

import com.TeamC.Chapter6.DTO.FilmResponseDTO;
import lombok.*;

import javax.persistence.*;

@Table(name = "films")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @Column(name = "name")
    private String filmName;

    @Column(name = "is_playing")
    private Boolean isPlaying;

    public FilmResponseDTO convertToResponse() {
        return FilmResponseDTO.builder().filmId(this.filmId)
                .filmTitle(this.filmName)
                .status(this.isPlaying)
                .build();
    }

    @Override
    public String toString() {
        return "Films{" +
                "filmId=" + filmId +
                ", name='" + filmName + '\'' +
                ", isPlaying=" + isPlaying +
                '}';
    }
}
