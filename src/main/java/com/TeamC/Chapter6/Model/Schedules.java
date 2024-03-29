package com.TeamC.Chapter6.Model;


import com.TeamC.Chapter6.DTO.SchedulesResponseDTO;
import com.TeamC.Chapter6.DTO.SchedulesResponseFilm;
import com.TeamC.Chapter6.DTO.SchedulesResponsePost;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "schedules")
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film films;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_show")
    private LocalDate dateShow;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seats seats;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "show_start")
    private LocalTime showStart;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "show_end")
    private LocalTime showEnd;

    @Column(name = "price")
    private Integer price;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public SchedulesResponseDTO convertToResponse(){
        return SchedulesResponseDTO.builder()
                .scheduleId(this.scheduleId).films(this.films)
                .seats(this.seats).dateShow(this.dateShow)
                .showStart(this.showStart).showEnd(this.showEnd).price(this.price)
                .created_at(this.createdAt).updated_at(this.updatedAt).build();
    }

    public SchedulesResponsePost convertToResponsePost(){
        return SchedulesResponsePost.builder()
                .scheduleId(this.scheduleId)
                .filmId(this.films.getFilmId())
                .seatId(this.seats.getSeatId())
                .dateShow(this.dateShow)
                .showStart(this.showStart)
                .showEnd(this.showEnd)
                .price(this.price)
                .updatedAt(this.updatedAt)
                .build();

    }
    public SchedulesResponseFilm convertToResponseNameLike(){
        return SchedulesResponseFilm.builder()
                .filmName(this.getFilms().getFilmName())
                .studioName(this.getSeats().getStudioName())
                .price(this.price)
                .created_at(this.createdAt)
                .updatedAt(this.updatedAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleId=" + scheduleId +
                ", films=" + films +
                ", dateShow=" + dateShow +
                ", seats=" + seats +
                ", showStart=" + showStart +
                ", showEnd=" + showEnd +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

}
