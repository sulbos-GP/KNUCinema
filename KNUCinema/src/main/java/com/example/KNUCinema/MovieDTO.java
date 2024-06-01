package com.example.KNUCinema;

import lombok.*;

import java.sql.Time;
import java.util.Map;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private int id;
    private String title;
    private String image;
    private String content;
}


