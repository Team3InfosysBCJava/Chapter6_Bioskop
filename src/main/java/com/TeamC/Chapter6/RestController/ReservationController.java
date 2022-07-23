package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.ReservationRequestDTO;
import com.TeamC.Chapter6.DTO.ReservationResponseDTO;
import com.TeamC.Chapter6.DTO.ReservationResponsePost;
import com.TeamC.Chapter6.Helper.ResourceNotFoundException;
import com.TeamC.Chapter6.Model.Film;
import com.TeamC.Chapter6.Model.Reservation;
import com.TeamC.Chapter6.Service.ReservationServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.TeamC.Chapter6.Response.ResponseHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Tag(name = "7. Reservations Controller")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/team3")
@AllArgsConstructor
public class ReservationController {

    private static final Logger logger = LogManager.getLogger(ReservationController.class);
    private static final String Line = "====================";
    private ReservationServices reservationServices;

    /**
     *Get All Reservation from Reservation table
     * throws ResourceNotFoundException if not found happened
     */
    @GetMapping("/Reservation")
    public ResponseEntity<Object> findAll(){
        try {
            List<Reservation> reservations = reservationServices.getAll();
            List<ReservationResponseDTO> maps = new ArrayList<>();
            logger.info(Line + " Logger Start Find All Reservation " + Line);
            for (Reservation dataresults:reservations){
                logger.info("Reservation id : "+dataresults.getReservationId()+
                        ", Title Film : "+dataresults.getSchedule().getFilms().getFilmName()+
                        ", Status Playing : "+dataresults.getSchedule().getFilms().getIsPlaying()+
                        ", Studio : "+dataresults.getSchedule().getSeats().getStudioName()+
                        ", Seat Number : "+dataresults.getSchedule().getSeats().getSeatNumber()+
                        ", Status Seat : "+dataresults.getSchedule().getSeats().getIsAvailable()+
                        ", Price : "+dataresults.getSchedule().getPrice()+
                        ", Date Show : "+dataresults.getSchedule().getDateShow()+
                        ", Show Start : "+dataresults.getSchedule().getShowStart()+
                        ", Show End : "+dataresults.getSchedule().getShowEnd());
                ReservationResponseDTO reservationDTO = dataresults.convertToResponse();
                maps.add(reservationDTO);
            }
            logger.info(Line + " Logger End Find All Reservation " + Line);
            return ResponseHandler.generateResponse("Success Get All",HttpStatus.OK,maps);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Table has no value");
        }
    }

    /**
     *Get Reservation by Reservation id
     * throws ResourceNotFoundException if data is not found
     */
    @GetMapping("/Reservation/{id}")
    public ResponseEntity<Object> getReservationById(@PathVariable Long id){
        try {
            Optional<Reservation> reservation = reservationServices.getReservationById(id);
            Reservation reservationGet = reservation.get();
            ReservationResponseDTO result = reservationGet.convertToResponse();
            logger.info(Line + " Logger Start Get By id Reservation " + Line);
            logger.info("Update RReservation : " + result);
            logger.info(Line + " Logger End Get By id Reservation " + Line);
            return ResponseHandler.generateResponse("Success Get By Id",HttpStatus.OK,result);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }
    }

    /**
     * update Reservation
     * throws ResourceNotFoundException if data not found
     */
    @PutMapping("/Reservation/{id}")
    public ResponseEntity<Object> Reservationupdate(@PathVariable Long id, @RequestBody ReservationRequestDTO reservationRequestDTO){
        try {
            if(reservationRequestDTO.getSchedules() == null || reservationRequestDTO.getUser() == null){
                throw new ResourceNotFoundException("Reservation must have schedule id and user id");
            }
            Reservation Reservation = reservationRequestDTO.covertToEntitiy();
            Reservation.setReservationId(id);
            Reservation ReservationUpdate = reservationServices.updateReservation(Reservation);
            ReservationResponseDTO results = ReservationUpdate.convertToResponse();
            logger.info(Line + " Logger Start Update Reservation " + Line);
            logger.info("Update Reservation : " + ReservationUpdate);
            logger.info(Line + " Logger End Update Reservation " + Line);
            return ResponseHandler.generateResponse("Success Update Reservation",HttpStatus.CREATED,results);
        }catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,"Bad Request");
        }
    }
    /**
     *create new Reservation into Reservation table
     * throws ResourceNotFoundException if bad request happened
     */
    @PostMapping("/Reservation")
    public ResponseEntity<Object> ReservationCreate(@RequestBody ReservationRequestDTO reservationRequestDTO){
        try{
            if(reservationRequestDTO.getSchedules() == null || reservationRequestDTO.getUser() == null){
                throw new ResourceNotFoundException("Reservation must have schedule id and user id");
            }
            Reservation Reservation = reservationRequestDTO.covertToEntitiy();
            reservationServices.createReservation(Reservation);
            ReservationResponsePost result = Reservation.convertToResponsePost();
            logger.info(Line + " Logger Start Create Reservation " + Line);
            logger.info("Create Reservation : " + result);
            logger.info(Line + " Logger End Create Reservation " + Line);
            return ResponseHandler.generateResponse("Success Create Reservation",HttpStatus.CREATED,result);
        }catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,"Failed Create Database");
        }
    }

    /**
     * delete schedule by id
     * throws ResourceNotFoundException if data is not found
     */
    @DeleteMapping("/Reservation/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable Long id){
        try {
            reservationServices.deleteSReservationById(id);
            Boolean result = Boolean.TRUE;
            logger.info(Line + " Logger Start Delete Reservation " + Line);
            logger.info("Success Delete Reservatiom by ID :"+result);
            logger.info(Line + " Logger End Delete Reservation " + Line);
            return ResponseHandler.generateResponse("Success Delete Reservation by ID",HttpStatus.OK,result);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Data not found");
        }
    }

    /**
     * custom Query Reservation by filmname
     * Query Find by Filmnme
     *throws ResourceNotFoundException if film name is not found
     */
    @PostMapping("/Reservation/Filmname")
    public ResponseEntity<Object> findReservationBySchdeuleFilmName(@RequestBody Film films){
        try {
            List<Reservation> reservationsByScheduleFilmsname = reservationServices.getReservationByFilmName(films.getFilmName());
            List<ReservationResponseDTO> ReservationResponseDTOS = reservationsByScheduleFilmsname.stream()
                    .map(Reservation::convertToResponse)
                    .collect(Collectors.toList());
            logger.info(Line+" Logger Start Query By Film Name Reservation "+Line);
            logger.info("Success Query By Filmname : " +ReservationResponseDTOS);
            logger.info(Line+" Logger End Query By Film Name Reservation "+Line);
            return ResponseHandler.generateResponse("Succes Query By Filmname",HttpStatus.OK,ReservationResponseDTOS);
        }catch (Exception e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.BAD_REQUEST,"Failed Query By Filename");
        }

    }


}






