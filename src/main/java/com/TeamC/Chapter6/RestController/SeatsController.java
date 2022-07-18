package com.TeamC.Chapter6.RestController;

import com.TeamC.Chapter6.Model.Seats;
import com.TeamC.Chapter6.Service.SeatsService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("team3/v1")
@AllArgsConstructor
public class SeatsController {

//    private static final Logger logger = LogManager.getLogger(SeatsController.class);
    private SeatsService seatsService;

    /**
     * Get All
     */

    @GetMapping("/Seats")
    public List<Seats> getAll(){
        return seatsService.findAll();
    }
}
