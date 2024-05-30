package com.example.KNUCinema;

public interface KnuMovieService {
    public int count();
    public MovieDTO find(int id);
    public UserDTO findPhoneNumber(String phoneNumber);
}
