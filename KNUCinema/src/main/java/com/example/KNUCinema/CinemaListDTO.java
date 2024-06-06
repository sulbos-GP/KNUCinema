package com.example.KNUCinema;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CinemaListDTO extends MovieDTO  {
    private ArrayList<LocalDateTime> times;


    public  CinemaListDTO(MovieDTO movieDTO, ArrayList<LocalDateTime> times){
        super(movieDTO);
        this.times = times;
    }
}
