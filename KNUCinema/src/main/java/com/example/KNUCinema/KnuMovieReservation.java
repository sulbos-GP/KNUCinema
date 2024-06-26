package com.example.KNUCinema;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface KnuMovieReservation { //TODO : IK

    ArrayList<CinemaDTO> getMoviesByTime(String time); // 시간에 따라 가능한 모든 영화
    ArrayList<CinemaDTO> getMoviesByTime(String time, String name); // 시간과 영화이름에 따라
    ArrayList<CinemaDTO> getMoviesByCinemaId(int id); // 시네마 아이디에 따라 볼수 있는 영화 찾기
    ArrayList<CinemaDTO> getAllMovies(); // 시네마 아이디에 따라 볼수 있는 영화 찾기

    ArrayList<ReservationDTO> getReservationById(String number); // 전화 번호로 예약 정보 받기
    ArrayList<ReservationDTO> getReservationById(long id); // 전화 번호로 예약 정보 받기
    ArrayList<ReservationDTO> setReservation(CinemaDTO cinema, long UserId,String reserSeat); // 예약 정보 받기

    void setUserData(UserDTO userData);
}


