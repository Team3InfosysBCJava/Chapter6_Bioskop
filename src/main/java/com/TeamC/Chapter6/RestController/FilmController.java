package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.FilmRequestDTO;
import com.TeamC.Chapter6.DTO.FilmResponseDTO;
import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Service.FilmService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@Tag(name = "4. Film Controller")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/team3")
public class FilmController {

    @Autowired
    public FilmService filmService;
    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);
    private static final String Line = "====================";

    //CREATE
    @PostMapping("/dashboard/create/films")
    public ResponseEntity<Object> createFilm(@RequestBody FilmRequestDTO filmRequestDTO) {
        try {
            Film filmsCreate = filmRequestDTO.convertToEntity();
            filmService.addFilm(filmsCreate);
            FilmResponseDTO results = filmsCreate.convertToResponse();
            logger.info(Line + "Logger Start Create " + Line);
            logger.info(String.valueOf(results));
            logger.info(Line + "Logger End Create " + Line);
            return ResponseHandler.generateResponse("successfully created film", HttpStatus.CREATED, filmsCreate);
        } catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "no data");
        }
    }

    //READ
    @GetMapping("/films")
    public ResponseEntity<Object> getAllFilm(){
        try {
            List<Film> films = filmService.findAllFilm();
            List<FilmResponseDTO> result = new ArrayList<>();
            logger.info(Line + " Logger Start Get All " + Line);
            for(Film data : films){
                FilmResponseDTO filmResponseDTO = data.convertToResponse();
                result.add(filmResponseDTO);
                logger.info("seatId : " + data.getFilmId() + " film title : " + data.getFilmName() + " isPlaying : " + data.getIsPlaying());
            }
            logger.info(Line + " Logger End Get All " + Line);
            return ResponseHandler.generateResponse("successfully retrieved", HttpStatus.OK,result);
        } catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "no data");
        }
    }

    //READ BY ID
    @GetMapping("/films/{filmId}")
    public ResponseEntity<Object> getfilmById(@PathVariable Long filmId) {
            try{
                Optional<Film> films = filmService.findFilmById(filmId);
                Film filmget = films.get();
                FilmResponseDTO result = filmget.convertToResponse();
                logger.info(Line + "Logger Start Get By Id " + Line);
                logger.info(String.valueOf(result));
                logger.info(Line + "Logger End Get By Id " + Line);
                return ResponseHandler.generateResponse("successfully retrieved", HttpStatus.OK, result);
            } catch (Exception e){
                logger.error(Line + " Logger Start Error " + Line);
                logger.error(e.getMessage());
                logger.error(Line + " Logger End Error " + Line);
                return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "no data");
            }
    }

    //READ BY NAME
    @GetMapping("/films/name")
    public ResponseEntity<Object> getFilmByName(@RequestBody FilmRequestDTO filmRequestDTO) {
        try{
            List<Film> film = filmService.findFilmByName(filmRequestDTO.getFilmTitle());
            List<FilmResponseDTO> result = new ArrayList<>();
            logger.info(Line + " Logger Start Get All " + Line);
            for (Film data : film) {
                FilmResponseDTO filmResponseDTO = data.convertToResponse();
                result.add(filmResponseDTO);
                logger.info("seatId : " + data.getFilmId() + " film title : " + data.getFilmName() + " isPlaying : " + data.getIsPlaying());
            }
            logger.info(Line + " Logger End Get All " + Line);
            return ResponseHandler.generateResponse("successfully retrieved", HttpStatus.OK, result);
        } catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "no data");
        }
    }

    //UPDATE
    @PutMapping("/dashboard/update/films/{filmId}")
    public ResponseEntity<Object> filmsupdate(@PathVariable Long filmId, @RequestBody FilmRequestDTO filmRequestDTO) {
        try{
            Optional<Film> filmTarget = filmService.findFilmById(filmId);
            Film filmRequest = filmRequestDTO.convertToEntity();
            Film film = filmTarget.get();
            film.setFilmName(filmRequest.getFilmName());
            film.setIsPlaying(filmRequest.getIsPlaying());

            filmService.updateFilm(film);

            FilmResponseDTO result = film.convertToResponse();
            logger.info(Line + "Logger Start Update By Id " + Line);
            logger.info(String.valueOf(result));
            logger.info(Line + "Logger End Update By Id " + Line);
            return ResponseHandler.generateResponse("successfully updated film", HttpStatus.CREATED, result);
        } catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "no data");
        }
    }

    //DELETE
    @DeleteMapping("/dashboard/delete/schedules/{filmId}")
    public ResponseEntity<Object> deleteFilm(@PathVariable Long filmId) {
        try{
            filmService.deleteFilm(filmId);
            logger.info(Line + "Logger Start Delete By Id " + Line);
            logger.info("Delete Success");
            logger.info(Line + "Logger End Delete By Id " + Line);
            return ResponseHandler.generateResponse("successfully deleted film", HttpStatus.OK, null);
        } catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, "failed");
        }
    }
}