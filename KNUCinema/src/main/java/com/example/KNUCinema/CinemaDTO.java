package com.example.KNUCinema;

import lombok.*;

import java.util.ArrayList;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor

public class CinemaDTO {
    private int id;
    private String name;
    private ArrayList<MovieDTO> movies;
    
}
