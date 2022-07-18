package com.TeamC.Chapter6.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatsResponseDTO {
    private Long seat_id;
    private Integer number_seat;
    private String name_studio;
    private Boolean available;

    @Override
    public String toString() {
        return "SeatsResponseDTO{" +
                "seat_id=" + seat_id +
                ", number_seat=" + number_seat +
                ", name_studio='" + name_studio + '\'' +
                ", available=" + available +
                '}';
    }
}
