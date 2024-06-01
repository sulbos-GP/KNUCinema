package com.example.KNUCinema;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private int id;
    private CinemaDTO cinema;
    private int UserId;
}
