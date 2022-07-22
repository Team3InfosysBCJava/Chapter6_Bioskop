package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.FilmRequestDTO;
import com.TeamC.Chapter6.DTO.FilmResponseDTO;
import com.TeamC.Chapter6.Helper.ResourceNotFoundException;
import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class FilmController {

    @Autowired
    public FilmService filmService;


    //CREATE
    @PostMapping("films/create-film")
    public ResponseEntity<Object> createFilm(@RequestBody FilmRequestDTO filmRequestDTO) {
            Film filmsCreate = filmRequestDTO.convertToEntity();
            filmService.addFilm(filmsCreate);
            return ResponseHandler.generateResponse("Succes Create", HttpStatus.CREATED, filmsCreate);
    }

    //READ
    @GetMapping("/films")
    public ResponseEntity<Object> getAllFilm(){
        List<Film> films = filmService.findAllFilm();
        List<FilmResponseDTO> result = new ArrayList<>();
        for(Film film : films){
            FilmResponseDTO filmResponseDTO =film.convertToResponse();
            result.add(filmResponseDTO);
        }
        return ResponseHandler.generateResponse("Succes All", HttpStatus.OK,result);
    }



    //READ BY ID
    @GetMapping("/films/{filmId}")
    public ResponseEntity<Object> getfilmById(@PathVariable Long filmId) {
            Optional<Film> films = filmService.findFilmById(filmId);
            Film filmget = films.get();
            FilmResponseDTO filmsResponseDTO = filmget.convertToResponse();
            return ResponseHandler.generateResponse("Succes Get", HttpStatus.OK, filmsResponseDTO);
    }


    //READ BY NAME
    @GetMapping("/films/name")
    public ResponseEntity<Object> getFilmByName(String name) {
        List<Film> film = filmService.findFilmByName(name);
        List<FilmResponseDTO> result = new ArrayList<>();

        for (Film data : film) {
            FilmResponseDTO filmResponseDTO = data.convertToResponse();
            result.add(filmResponseDTO);
        }

        return ResponseHandler.generateResponse("Succes All", HttpStatus.OK, result);
    }


    //UPDATE
    @PutMapping("/films/update/{filmId}")
    public ResponseEntity<Object> filmsupdate(@PathVariable Long filmId, @RequestBody FilmRequestDTO filmRequestDTO) {
        Film film = filmRequestDTO.convertToEntity();
        film.setFilmId(filmId);
        Film filmsUpdate = filmService.updateFilm(film);

        return ResponseHandler.generateResponse("Succes Update", HttpStatus.CREATED, filmsUpdate);
    }


    //DELETE
    @DeleteMapping("films/delete/{filmId}")
    public ResponseEntity<Object> deleteFilm(@PathVariable Long filmId) {
        filmService.deleteFilm(filmId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseHandler.generateResponse("Succes Delete", HttpStatus.OK, response);
    }

}
