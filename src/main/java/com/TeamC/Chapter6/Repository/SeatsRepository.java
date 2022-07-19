package com.TeamC.Chapter6.Repository;

import com.TeamC.Chapter6.Model.Seats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Long> {

    @Query(value = "select * from seats s where is_available =?1", nativeQuery = true)
    public List<Seats> getSeatAvailable(Boolean isAvailable);

    @Query("SELECT s2 FROM Seats s2 WHERE CONCAT(s2.seatId, ' ', s2.seatNumber, ' ', s2.studioName, ' ', s2.isAvailable, ' ') LIKE %?1%")
    public List<Seats> search(String keyword);

    @Query("SELECT s2 FROM Seats s2 WHERE CONCAT(s2.seatId, ' ', s2.seatNumber, ' ', s2.studioName, ' ', s2.isAvailable, ' ') LIKE %?1%")
    public Page<Seats> Search(String keyword, Pageable pageable);


}
