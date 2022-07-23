package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.SeatsRequestDTO;
import com.TeamC.Chapter6.DTO.SeatsResponseDTO;
import com.TeamC.Chapter6.Model.Seats;
import com.TeamC.Chapter6.Response.ResponseHandler;
import com.TeamC.Chapter6.Service.SeatsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Tag(name = "5. Seats Controller")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/team3")
@AllArgsConstructor
public class SeatsController {

    private static final Logger logger = LogManager.getLogger(SeatsController.class);
    private static final String Line = "====================";
    private SeatsService seatsService;


    /**
     * Get All
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *     @GetMapping("/v1/Seats")
     *     public List<Seats> getAll() {
     *         return seatsService.findAll();
     *     }
     */
    @GetMapping("/v2/Seats")
    public ResponseEntity<Object> getAll2() {
        try {
            List<Seats> seats = seatsService.findAll();
            List<SeatsResponseDTO> results = new ArrayList<>();
            logger.info(Line + " Logger Start Get All " + Line);
            for (Seats data : seats) {
                SeatsResponseDTO seatDTO = data.convertToResponse();
                results.add(seatDTO);
                logger.info("seatId : " + data.getSeatId() + " seatNumber : " + data.getSeatNumber() + " studioName : " + data.getStudioName() + " isAvailable : " + data.getIsAvailable());
            }
            logger.info(Line + " Logger End Get All " + Line);
            return ResponseHandler.generateResponse("Success Get All", HttpStatus.OK, results);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Seats has no value");
        }
    }

    /**
     * Get By id , @PathVariable Long id
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *     @GetMapping("/v1/Seats/{id}")
     *     public Seats getSeatById(@PathVariable Long id) {
     *         Optional<Seats> seats = seatsService.findById(id);
     *         return seats.get();
     *     }
     */
    @GetMapping("/v2/Seats/{id}")
    public ResponseEntity<Object> getSeatById2(@PathVariable Long id) {
        try {
            Optional<Seats> seats = seatsService.findById(id);
            Seats seatsGet = seats.get();
            SeatsResponseDTO result = seatsGet.convertToResponse();
            logger.info(Line + "Logger Start Get By Id " + Line);
            logger.info(result);
            logger.info(Line + "Logger End Get By Id " + Line);
            return ResponseHandler.generateResponse("Succes Get By id", HttpStatus.OK, result);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "seat no value");
        }
    }

    /**
     * Create , @RequestBody Seats seats
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *    @PostMapping("/v1/Seats")
     *     public Seats createSeat(@RequestBody Seats seats) {
     *         return seatsService.createSeat(seats);
     *     }
     */
    @PostMapping("/v2/Seats")
    public ResponseEntity<Object> createSeat2(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        try {
            Seats seat = seatsRequestDTO.convertToModel();
            seatsService.createSeat(seat);
            SeatsResponseDTO results = seat.convertToResponse();
            logger.info(Line + "Logger Start Create " + Line);
            logger.info(results);
            logger.info(Line + "Logger End Create " + Line);
            return ResponseHandler.generateResponse("Success Create Seat", HttpStatus.CREATED, results);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Failed Create Seat");
        }
    }

    /**
     * Update by ID
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *     @PutMapping("/v1/Seats/{id}")
     *     public Seats updateSeat(@PathVariable Long id, @Valid @RequestBody Seats seatsRequest) {
     *         Optional<Seats> seatId = seatsService.findById(id);
     *         Seats seats = seatId.get();
     *         seats.setSeatNumber(seatsRequest.getSeatNumber());
     *         seats.setStudioName(seatsRequest.getStudioName());
     *         seats.setIsAvailable(seatsRequest.getIsAvailable());
     *         return seatsService.updateSeat(seats);
     *     }
     */
    @PutMapping("/v2/Seats/{id}")
    public ResponseEntity<Object> updateSeat2(@PathVariable Long id, @RequestBody SeatsRequestDTO seatsRequestDTO) {
        try {
            Optional<Seats> seatId = seatsService.findById(id);
            Seats seatsRequest = seatsRequestDTO.convertToModel();
            Seats seats = seatId.get();
            seats.setSeatNumber(seatsRequest.getSeatNumber());
            seats.setStudioName(seatsRequest.getStudioName());
            seats.setIsAvailable(seatsRequest.getIsAvailable());
            seatsService.updateSeat(seats);
            SeatsResponseDTO results = seats.convertToResponse();
            logger.info(Line + "Logger Start Update By Id " + Line);
            logger.info(results);
            logger.info(Line + "Logger End Update By Id " + Line);
            return ResponseHandler.generateResponse("Success Update Seat", HttpStatus.OK, results);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Failed Update Seat");
        }
    }

    /**
     * Delete by ID
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *     @DeleteMapping("/v1/Seats/{id}")
     *     public void deleteSeat(@PathVariable Long id) {
     *         seatsService.deleteSeat(id);
     *     }
     */
    @DeleteMapping("/v2/Seats/{id}")
    public ResponseEntity<Object> deleteSeat2(@PathVariable Long id) {
        try {
            seatsService.deleteSeat(id);
            logger.info(Line + "Logger Start Delete By Id " + Line);
            logger.info("Delete Success");
            logger.info(Line + "Logger End Delete By Id " + Line);
            return ResponseHandler.generateResponse("Success Delete", HttpStatus.OK, null);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Failed Delete Seat");
        }
    }

    /**
     * Custom Query
     * v1 = bentuk paling sederhana
     * v2 = versi akhir
     *     @PostMapping("/v1/Seats/isAvailable")
     *     public List<Seats> getIsAvailable(@RequestBody Seats seatsRequest) {
     *         return seatsService.getSeatsAvailable(seatsRequest.getIsAvailable());
     *     }
     */
    @PostMapping("/v2/Seats/isAvailable")
    public ResponseEntity<Object> getIsAvailable2(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        try {
            Seats seatsRequest = seatsRequestDTO.convertToModel();
            List<Seats> seats = seatsService.getSeatsAvailable(seatsRequest.getIsAvailable());
            List<SeatsResponseDTO> results = seats.stream()
                    .map(Seats::convertToResponse)
                    .collect(Collectors.toList());
            logger.info(Line + "Logger Start Query " + Line);
            logger.info(results);
            logger.info(Line + "Logger End Query " + Line);
            return ResponseHandler.generateResponse("Success Query", HttpStatus.OK, results);
        } catch (Exception e) {
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Failed Delete Seat");
        }
    }
}
