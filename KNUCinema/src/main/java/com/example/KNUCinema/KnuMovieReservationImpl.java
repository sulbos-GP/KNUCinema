package com.example.KNUCinema;

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
    public  ArrayList<CinemaDTO> cinema = new ArrayList<>();
    public ArrayList<MovieDTO> movies = new ArrayList<>();
    public ArrayList<UserDTO> userDB = new ArrayList<>();
    private int selectedTheater;

    public KnuMovieReservationImpl() {
        this.movies = new ArrayList<>();
        this.cinema = new ArrayList<>();
        this.selectedTheater = -1;


        //Todo : 삭제
        System.out.println("객체 생성");
        int[][] seat =  new int[10][10];
        for (int[] ints : seat) Arrays.fill(ints,0);

        movies.add(new MovieDTO(0, "아바타", "/image/Avatar.png", "영화 내용 10"));
        movies.add(new MovieDTO(1, "탑건", "/image/TopGun.png", "영화 내용 1"));
        movies.add(new MovieDTO(2, "인셉션", "/image/Inception.png", "영화 내용 2"));
        movies.add(new MovieDTO(3, "인터스텔라", "/image/Interstellar.png", "영화 내용 3"));
        movies.add(new MovieDTO(4, "어벤져스", "/image/Avengers.png", "영화 내용 4"));
        movies.add(new MovieDTO(5, "기생충", "/image/Parasite.png", "영화 내용 5"));
        movies.add(new MovieDTO(6, "라라랜드", "/image/LaLaLand.png", "영화 내용 6"));
        movies.add(new MovieDTO(7, "알라딘", "/image/Aladdin.png", "영화 내용 7"));
        movies.add(new MovieDTO(8, "조커", "/image/Joker.png", "영화 내용 8"));
        movies.add(new MovieDTO(9, "토이 스토리", "/image/ToyStory.png", "영화 내용 9"));


        //user 메모리db생성
        userDB.add(new UserDTO(1,"홍성현",26,"01092059813","탑건"));


        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            cinema.add(new CinemaDTO(1, currentDate.atStartOfDay(), seat, movies.get(i)));
        }


    }


    @Override
    public ArrayList<CinemaDTO> getMoviesByTime(String time) {  
        //시간으로 시네마 DTO 받기
        ArrayList<CinemaDTO> currentList = new ArrayList<>();

        cinema.stream()
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

        cinema.stream()
                .filter(m -> m.getTime().equals(localDate) && m.getTitle().equals(title))
                .forEach(currentList::add);

        return currentList; // 결과를 ArrayList로 반환

    }

    @Override
    public ArrayList<CinemaDTO> getMoviesByCinemaId(int id) {
        // 시네마 아이디에 따라 볼수 있는 영화 찾기
        ArrayList<CinemaDTO> currentList = new ArrayList<>();

        cinema.stream()
                .filter(m -> m.getCid() == id)
                .forEach(currentList::add);

        return currentList; // 결과를 ArrayList로 반환

    }

    @Override
    public ArrayList<MovieDTO> CurrentBestMovie() { // TODO: DB 연결하면 그때 구현
        return null;
    }



   






}

