package com.example.KNUCinema;

import java.util.ArrayList;

public interface DatabaseDAO {


    ArrayList<CinemaDTO> getCinema();
    ArrayList<MovieDTO> getMovieD();
    ArrayList<UserDTO> getUser();
}
