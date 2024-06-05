package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class KnuMovieReservationImpl implements KnuMovieReservation
{
    //DB 가져오기
    @Autowired
    private DatabaseDAO DB;

    private int selectedTheater;

    public KnuMovieReservationImpl() {

        this.selectedTheater = -1;

    }


    @Override
    public ArrayList<CinemaDTO> getMoviesByTime(String time) {  
        //시간으로 시네마 DTO 받기
        ArrayList<CinemaDTO> currentList = new ArrayList<>();
        DB.getCinema().stream()
                .filter(m -> m.getTime().equals(time))
                .forEach(currentList::add);


        return currentList;
    }

    @Override
    public ArrayList<CinemaDTO> getMoviesByTime(String time, String title) {
        //시간과 이름으로 시네마 DTO 받기
        ArrayList<CinemaDTO> currentList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(time, formatter);

        DB.getCinema().stream()
                .filter(m -> m.getTime().equals(localDate) && m.getTitle().equals(title))
                .forEach(currentList::add);

        return currentList; // 결과를 ArrayList로 반환

    }




    @Override
    public ArrayList<CinemaDTO> getMoviesByCinemaId(int id) {
        // 시네마 아이디에 따라 볼수 있는 영화 찾기
        ArrayList<CinemaDTO> currentList = new ArrayList<>();

        DB.getCinema().stream()
                .filter(m -> m.getCid() == id)
                .forEach(currentList::add);

        return currentList; // 결과를 ArrayList로 반환

    }

    @Override
    public ArrayList<MovieDTO> CurrentBestMovie() { // TODO: DB 연결하면 그때 구현
        return null;
    }



   






}

