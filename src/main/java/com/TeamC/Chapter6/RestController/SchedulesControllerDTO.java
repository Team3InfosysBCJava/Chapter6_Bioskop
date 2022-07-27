package com.TeamC.Chapter6.RestController;


import com.TeamC.Chapter6.DTO.SchedulesRequestDTO;
import com.TeamC.Chapter6.DTO.SchedulesResponseDTO;
import com.TeamC.Chapter6.DTO.SchedulesResponseFilm;
import com.TeamC.Chapter6.DTO.SchedulesResponsePost;
import com.TeamC.Chapter6.Helper.ExceptionHandler;
import com.TeamC.Chapter6.Model.*;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Tag(name = "6. Schedules Controller")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/team3")
@AllArgsConstructor
public class SchedulesControllerDTO {

    private static final Logger logger = LoggerFactory.getLogger(FilmController.class);

    private static final String Line = "============================================================";

    private SchedulesService SchedulesService;

    /**
     *Get all of data from Scheduless table
     */
    @GetMapping("/Schedules")
    public ResponseEntity<Object> SchedulesList(){
        try {
            List<Schedules> result = SchedulesService.getAll();
            List<SchedulesResponseDTO> SchedulesMaps = new ArrayList<>();
            logger.info(Line + "Logger Start Find All Schedules" + Line);
            for (Schedules dataResult:result){
                logger.info("================================");
                logger.info("Schedules id :"+dataResult.getScheduleId());
                logger.info("Film :"+dataResult.getFilms());
                logger.info("Seats :"+dataResult.getSeats());
                logger.info("================================");
                SchedulesResponseDTO SchedulesDTO = dataResult.convertToResponse();
                SchedulesMaps.add(SchedulesDTO);
            }
            logger.info(Line + "Logger End Find All Schedules" + Line);
            return ResponseHandler.generateResponse("Successfully  getAll data!", HttpStatus.OK, SchedulesMaps);
        }
        catch (ExceptionHandler e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND, "Tabel has no Value");
        }
    }

    /**
     *create new Schedules into Scheduless table
     * throws ResourceNotFoundException if bad request happened
     */
    @PostMapping("/Schedules")
    public ResponseEntity<Object> createSchedulesDTO(@RequestBody SchedulesRequestDTO SchedulesRequestDTO) {
        try {
            Schedules SchedulesCreate = SchedulesRequestDTO.convertToEntity();
            SchedulesService.createSchedules(SchedulesCreate);
            SchedulesResponsePost result = SchedulesCreate.convertToResponsePost();
            logger.info(Line + " Logger Start Created New Schedules" + Line);
            logger.info(String.valueOf(result));
            logger.info(Line + " Logger Stop Create New Schedules" + Line);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Bad Request!!");
        }
    }

    /**
     * update Schedules
     * throws ResourceNotFoundException if data not found
     */
    @PutMapping("/Schedules/{id}")
    public ResponseEntity<Object> updateSchedulesDTO(@PathVariable Integer id, @RequestBody SchedulesRequestDTO SchedulesRequestDTO) {
        try {

            Schedules Schedules = SchedulesRequestDTO.convertToEntity();
            Schedules.setScheduleId(id);
            Schedules SchedulesUpdate = SchedulesService.updateSchedules(Schedules);
            SchedulesResponseDTO result = SchedulesUpdate.convertToResponse();
            logger.info(Line + " Logger Start Updated Data" + Line);
            logger.info("Update : " + SchedulesUpdate);
            logger.info(Line + " Logger Finish Updated Data" + Line);
            return ResponseHandler.generateResponse("Data Updated!", HttpStatus.CREATED, result);
        } catch (Exception e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Bad Request");
        }
    }
    /**
     * delete Schedules by id
     * throws ResourceNotFoundException if data is not found
     */
    @DeleteMapping("/Schedules/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable Integer id) {
        try {
            SchedulesService.deleteSchedulesById(id);
            Boolean result = Boolean.TRUE;
            logger.info(Line + " Logger Start Delete Schedules " + Line);
            logger.info("Success Delete Schedules by ID :" + result);
            logger.info(Line + " Logger End Delete Schedules " + Line);
            return ResponseHandler.generateResponse("Success Delete Booking by ID", HttpStatus.OK, result);
        } catch (ExceptionHandler e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!");
        }
    }
    /**
     *Get Schedules by Schedules id
     * throws ResourceNotFoundException if data is not found
     */
    @GetMapping("/Schedules/{id}")
    public ResponseEntity<Object> getSchedulesById(@PathVariable Integer id) {
        try {
            Optional<Schedules> Schedules = SchedulesService.getSchedulesById(id);
            Schedules Schedulesget = Schedules.get();
            SchedulesResponseDTO result = Schedulesget.convertToResponse();
            logger.info(Line + " Logger Start Find Schedules ById " + Line);
            logger.info("GetById");
            logger.info(String.valueOf(result));
            logger.info(Line + " Logger End Find Schedules By Id");
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, result);
        } catch (ExceptionHandler e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Data Not Found!"); }
    }

    /**
     * custom Challange 4 slide 8 nomor 2.2
     * Query Find colum Film,Studio Name, Price
     *throws ResourceNotFoundException if film name is not found
     */
    @PostMapping("/Schedules/byfilmnameLike")
    public ResponseEntity<Object> findSchedulesByFilmName(@RequestBody Film film) {
        try {
            List<Schedules> SchedulesByFilmName = SchedulesService.getSchedulesByFilmNameLike(film.getFilmName());
            List<SchedulesResponseFilm> results = SchedulesByFilmName.stream()
                    .map(Schedules::convertToResponseNameLike)
                    .collect(Collectors.toList());
            logger.info(Line + "Logger Start Create New Schedules" + Line);
            logger.info("Get Response :" + results);
            logger.info(Line + "Logger Finish Create New Schedules" + Line);
            return ResponseHandler.generateResponse("Success" ,HttpStatus.CREATED,results);
        } catch (Exception e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST, "Bad Request");

        }

    }
}
