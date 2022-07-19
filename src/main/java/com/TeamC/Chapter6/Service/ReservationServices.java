package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.Reservation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReservationServices {
    List<Reservation> getAll();
    Optional<Reservation> getReservationById(Long Id);
    Reservation createReservation(Reservation Reservation);
    void deleteSReservationById(Long Id);
    Reservation updateReservation(Reservation Reservation);
    Reservation getReferenceById (Long Id);
    List<Reservation> getReservationByFilmName(String name);
    Page<Reservation> getReservationFilm(String name , String page);
    Page<Reservation> search(String keyword,String page);
    Integer pageUpdate(String page);
    Page<Reservation> getReservationId(Long id, String page);
    Page<Reservation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
