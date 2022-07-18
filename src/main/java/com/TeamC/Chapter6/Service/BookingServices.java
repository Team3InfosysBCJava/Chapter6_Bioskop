package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.Reservation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookingServices {
    List<Reservation> getAll();
    Optional<Reservation> getBookingById(Long Id);
    Reservation createBooking(Reservation booking);
    void deleteSBookingById(Long Id);
    Reservation updateBooking(Reservation booking);
    Reservation getReferenceById (Long Id);
    List<Reservation> getBookingByFilmName(String name);
    Page<Reservation> getBookingFilm(String name , String page);
    Page<Reservation> search(String keyword,String page);
    Integer pageUpdate(String page);
    Page<Reservation> getBookingId(Long id, String page);
    Page<Reservation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
