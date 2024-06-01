package com.example.KNUCinema;

import lombok.*;

import java.sql.Time;
import java.util.ArrayList;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class CinemaDTO extends MovieDTO {
    private int id;
    private String name;
    private Time time;
    private int[][] seat;
}
