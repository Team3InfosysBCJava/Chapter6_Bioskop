package com.TeamC.Chapter6.DTO;


import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Model.Schedules;
import com.TeamC.Chapter6.Model.Seats;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulesRequestDTO {

    private Integer scheduleId;
    private Film films;
    private Seats seats;
    private LocalDate dateShow;
    private LocalTime showStart;
    private LocalTime showEnd;
    private Integer price;

    public Schedules convertToEntity(){
        return Schedules.builder().scheduleId(this.scheduleId).films(this.films).seats(this.seats)
                .dateShow(this.dateShow).showStart(this.showStart)
                .showEnd(this.showEnd).price(this.price)
                .build();
    }
}
