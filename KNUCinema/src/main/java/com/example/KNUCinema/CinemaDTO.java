package com.example.KNUCinema;

import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class CinemaDTO extends MovieDTO {
    private int id;
    private Time time;
    private Seat seat;

    public CinemaDTO(int id,Time time,int[][] seat,MovieDTO movieDTO)
    {
        super(movieDTO);
        this.id=id;
        this.time=time;
        this.seat = new Seat(10, 10);
    }

    @Getter @Setter @ToString
    @NoArgsConstructor
    public static class Seat{
        private int row;
        private int col;
        private int[][] seat;
        public Seat(int row,int col){
            this.row = row;
            this.col = col;
            this.seat = new int[row][col];
            for (int[] ints : seat) Arrays.fill(ints,0);
            System.out.println("Seat 초기화");
        }
    }
}
