package com.TeamC.Chapter6.Repository;

import com.TeamC.Chapter6.Model.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query(value = "SELECT * FROM films WHERE is_playing=?1", nativeQuery = true)
    public List<Film> getFilmByStatus(Boolean isPlaying);

    @Query("SELECT f FROM Film f where f.filmName LIKE %:name& ORDER BY f.filmName ASC")
    public List<Film> getFilmByName(@Param("name") String name);

}
