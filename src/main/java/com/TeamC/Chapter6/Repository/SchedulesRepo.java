package com.TeamC.Chapter6.Repository;

import com.TeamC.Chapter6.Model.Schedules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchedulesRepo extends JpaRepository<Schedules, Integer> {

    @Query("Select s from Schedules s where s.films.filmName =?1")
    public List<Schedules> getSchedulesByFilmName(String name);

    @Query("Select s from Schedules s where s.films.filmName like %:name%")
    public List<Schedules> getSchedulesFilmsNameLike (@Param("name")String name);

    @Query("Select s from Schedules s where s.films.filmName like %:name% ORDER BY s.films.filmName ASC")
    public Page<Schedules> searchByName(@Param("name")String name, Pageable pageable);

}
