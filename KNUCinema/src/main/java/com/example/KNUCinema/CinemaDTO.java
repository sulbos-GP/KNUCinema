package com.example.KNUCinema;

import lombok.*;

import java.sql.Time;
import java.util.ArrayList;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class CinemaDTO extends MovieDTO {
    private int id;
    private Time time;
    private int[][] seat;

    public CinemaDTO(int id,Time time,int[][] seat,MovieDTO movieDTO)
    {
        super(movieDTO);
        this.id=id;
        this.time=time;
        this.seat=seat;
    }
}
