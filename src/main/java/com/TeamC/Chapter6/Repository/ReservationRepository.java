package com.TeamC.Chapter6.Repository;

import com.TeamC.Chapter6.Model.Reservation;
import com.TeamC.Chapter6.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("Select r from Reservation r where r.schedule.films.filmName like %:name%")
    public List<Reservation> getReservationByFilmName(@Param("name")String name);

    @Query("Select r from Reservation r where r.user.userName =?1")
    public Reservation getReservationByUsername(String name);

    @Query("SELECT r FROM Reservation r WHERE CONCAT(r.reservationId, ' ', " +
            "r.schedule.films.filmName, ' ', r.schedule.films.isPlaying, ' ', " +
            "r.schedule.scheduleId, ' ', r.schedule.dateShow, ' ', r.schedule.showStart, ' ', r.schedule.showEnd, ' ', r.schedule.price, ' ', " +
            "r.schedule.seats.seatId, ' ', r.schedule.seats.seatNumber, ' ', r.schedule.seats.studioName, ' ', r.schedule.seats.isAvailable, ' ', " +
            "r.user.userId, ' ', r.user.userName, ' ', r.user.emailId) LIKE %?1% ORDER BY r.reservationId ASC")
    public Page<Reservation> search(String keyword, Pageable pageable);

    @Query("Select r from Reservation r where r.schedule.films.filmName like %:name% ORDER BY r.schedule.films.filmName ASC")
    public Page<Reservation> getReservationFilm(@Param("name")String name , Pageable pageable);

    @Query("Select r from Reservation r where r.reservationId =?1 ORDER BY r.reservationId ASC")
    public Page<Reservation> getReservationId(Long id , Pageable pageable);
}
