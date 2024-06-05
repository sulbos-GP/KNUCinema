package com.example.KNUCinema;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private long id;
    private CinemaDTO cinema;
    private int UserId;
}

//TODO : id는 자동으로 set금지 하는 어노테이션