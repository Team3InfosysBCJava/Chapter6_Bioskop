package com.TeamC.Chapter6.Model;

import com.TeamC.Chapter6.DTO.SeatsResponseDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Seats {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long seatId;

    private Integer seatNumber;

    private String studioName;

    private Boolean isAvailable;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public SeatsResponseDTO convertToResponse(){
        return SeatsResponseDTO.builder()
                .seat_id(this.seatId)
                .name_studio(this.studioName)
                .number_seat(this.seatNumber)
                .available(this.isAvailable)
                .build();
    }

    @Override
    public String toString() {
        return "Seats{" +
                "seatId=" + seatId +
                ", seatNumber=" + seatNumber +
                ", studioName='" + studioName + '\'' +
                ", isAvailable=" + isAvailable +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
