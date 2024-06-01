package com.example.KNUCinema;

import java.util.ArrayList;

public interface KnuMovieReservation {

    void selectTheater(int theaterId);
    ArrayList<MovieDTO> getMoviesByTime(String time);
    void reserveSeats(int movieId, int[][] seats);
}


