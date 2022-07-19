package com.TeamC.Chapter6.DTO;

import com.TeamC.Chapter6.Model.Reservation;
import com.TeamC.Chapter6.Model.Schedules;
import com.TeamC.Chapter6.Model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {
    private Long id;
    private User user;
    private Schedules schedules;

    public Reservation covertToEntitiy(){
        return Reservation.builder()
                .reservationId(this.id)
                .user(this.user)
                .schedule(this.schedules)
                .build();
    }
}

