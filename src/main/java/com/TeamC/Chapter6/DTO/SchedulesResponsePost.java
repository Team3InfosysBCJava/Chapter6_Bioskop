package com.TeamC.Chapter6.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchedulesResponsePost {

    private Integer scheduleId;
    private Long filmId;
    private Long seatId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateShow;

    //    @JsonFormat(pattern = "HH-mm-ss")
    private LocalTime showStart;

    //    @JsonFormat(pattern = "HH-mm-ss")
    private LocalTime showEnd;

    private Integer price;

    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "SchedulesResponseFilmSeatDTO{" +
                "scheduleId=" + scheduleId +
                ", filmId=" + filmId +
                ", seatId=" + seatId +
                ", dateShow=" + dateShow +
                ", showStart=" + showStart +
                ", showEnd=" + showEnd +
                ", price=" + price +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
