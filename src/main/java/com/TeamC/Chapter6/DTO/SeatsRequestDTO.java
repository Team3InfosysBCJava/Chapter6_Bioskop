package com.TeamC.Chapter6.DTO;

import com.TeamC.Chapter6.Model.Seats;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatsRequestDTO {
    private Long seat_id;
    private Integer number_seat;
    private String name_studio;
    private Boolean available;

    public Seats convertToModel(){
        return Seats.builder()
                .seatId(this.seat_id)
                .seatNumber(this.number_seat)
                .studioName(this.name_studio)
                .isAvailable(this.available)
                .build();
    }
}
