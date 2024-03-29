package com.TeamC.Chapter6.DTO;

import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Model.Seats;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulesResponseDTO {

    private Integer scheduleId;
    private Film films;
    private Seats seats;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateShow;

    private LocalTime showStart;

    private LocalTime showEnd;

    private Integer price;

    private LocalDateTime updated_at;
    private LocalDateTime created_at;

    @Override
    public String toString() {
        return "SchedulesResponseDTO{" +
                "scheduleId=" + scheduleId +
                ", films=" + films +
                ", seats=" + seats +
                ", dateShow=" + dateShow +
                ", showStart=" + showStart +
                ", showEnd=" + showEnd +
                ", price=" + price +
                ", updated_at=" + updated_at +
                ", created_at=" + created_at +
                '}';
    }
}
