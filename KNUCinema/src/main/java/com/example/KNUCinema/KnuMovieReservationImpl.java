package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class KnuMovieReservationImpl implements  KnuMovieReservation
{
    private int[][] seat;

    private int selectedTheater;



    private KnuMovieServiceImpl cinemaService;


    @Autowired
    public KnuMovieReservationImpl(KnuMovieServiceImpl cinemaService) {

        this.cinemaService = cinemaService;

        cinemaService.movie = new ArrayList<>();
        for (int[] ints : seat) Arrays.fill(ints,0);

        this.movie.add(new CinemaDTO(1,"jish",new Time(1),seat));

        this.selectedTheater = -1;
    }

    @Override
    public void selectTheater(int theaterId) {
        this.selectedTheater = theaterId;
        // 영화관 선택
        loadMoviesForTheater(theaterId);
    }

    @Override
    public ArrayList<MovieDTO> getMoviesByTime(String time) {
        ArrayList<MovieDTO> moviesByTime = new ArrayList<>();

        for (MovieDTO movie : movies) {
            moviesByTime.add(movie);
        }
        return moviesByTime;
    }

    @Override
    public void reserveSeats(int movieId, int[][] seats) {
        for (MovieDTO movie : movies) {
            if (movie.getId() == movieId) {
                int[][] currentSeats = movie.getSeat();
                for (int[] seat : seats) {
                    currentSeats[seat[0]][seat[1]] = 1;
                }
                movie.setSeat(currentSeats);
                break;
            }
        }
    }

    private void loadMoviesForTheater(int theaterId) {
        movies.clear();
        movies.add(new MovieDTO(1, new Time(10),"Movie 1", "image1.jpg", "Description 1", new int[10][10]));
        movies.add(new MovieDTO(2, new Time(10), "Movie 2", "image2.jpg", "Description 2", new int[10][10]));

    }






}

