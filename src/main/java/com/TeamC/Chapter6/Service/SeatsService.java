package com.TeamC.Chapter6.Service;

import com.TeamC.Chapter6.Model.Seats;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SeatsService {

    List<Seats> findAll();
    Optional<Seats> findById(Long id);
    Seats getReferenceById(Long id);
    Seats createSeat(Seats seats);
    Seats updateSeat(Seats seats);
    void deleteSeat(Long id);

    List<Seats> getSeatsAvailable(Boolean isAvailable);
    Page<Seats> search(String keyword, Integer page);
    Integer pageUpdate(String page);

    }
