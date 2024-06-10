package com.example.KNUCinema;

import java.time.LocalDateTime;

public interface KnuMovieService {
    public int count();
    public MovieDTO find(int id);
    public UserDTO findUser(String phoneNumber);
    public UserDTO findName(String name);
    public CinemaDTO findCinemaDTO(int id);
    public CinemaDTO findTimeCinemaDTO(int id, LocalDateTime time);
}
