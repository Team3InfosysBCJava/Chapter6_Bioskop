package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.DTO.BookingRequestDTO;
import com.TeamC.Chapter6.DTO.BookingResponseDTO;
import com.TeamC.Chapter6.Model.Reservation;
import com.TeamC.Chapter6.Service.BookingServices;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teamC/v1/DTO")
@AllArgsConstructor
public class ReservationController {
    private static final Logger logger = (Logger) LogManager.getLogger(ReservationController.class);
    private static final String Line = "====================";
    private BookingServices bookingService;
    private ScheduleService scheduleService;
    private SeatsService seatsService;


    /**
     *Get All booking from booking table
     * throws ResourceNotFoundException if not found happened
     */
    @GetMapping("/Reservation")
    public ResponseEntity<Object> findAll(){
        try {
            List<Reservation> bookings = bookingService.getAll();
            List<BookingResponseDTO> bookingmaps = new ArrayList<>();
            logger.info(Line + " Logger Start Find All Reservation " + Line);
            for (Reservation dataresults:bookings){
                logger.info("Reservation id : "+dataresults.getReservationId()+
                        ", Title Film : "+dataresults.getSchedule().getFilms().getName()+
                        ", Status Playing : "+dataresults.getSchedule().getFilms().getIsPlaying()+
                        ", Studio : "+dataresults.getSchedule().getSeats().getStudioName()+
                        ", Seat Number : "+dataresults.getSchedule().getSeats().getSeatNumber()+
                        ", Status Seat : "+dataresults.getSchedule().getSeats().getIsAvailable()+
                        ", Price : "+dataresults.getSchedule().getPrice()+
                        ", Date Show : "+dataresults.getSchedule().getDateShow()+
                        ", Show Start : "+dataresults.getSchedule().getShowStart()+
                        ", Show End : "+dataresults.getSchedule().getShowEnd());
                BookingResponseDTO bookDTO = dataresults.convertToResponse();
                bookingmaps.add(bookDTO);
            }
            logger.info(Line + " Logger End Find All Reservation " + Line);
            return ResponseHandler.generateResponse("Success Get All", HttpStatus.OK,bookingmaps);
        }catch(ResourceNotFoundException e){
            logger.error(Line + " Logger Start Error " + Line);
            logger.error(e.getMessage());
            logger.error(Line + " Logger End Error " + Line);
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,"Table has no value");
        }
    }

}
