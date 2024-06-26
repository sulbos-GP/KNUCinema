package com.example.KNUCinema;

import java.util.ArrayList;

public interface DatabaseDAO  {


    ArrayList<CinemaDTO> getCinema();
    ArrayList<MovieDTO> getMovie();
    ArrayList<ReservationDTO> getReservation();



    ArrayList<ReservationDTO> setReservation(ReservationDTO reservationDTO) ;

    void setUserData(UserDTO userDTO);

    ArrayList<UserDTO> getUser();
}
