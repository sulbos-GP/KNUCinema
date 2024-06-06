package com.example.KNUCinema;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lombok.Getter;


@Service
public class DataBaseImple implements DatabaseDAO{

    public  DataBaseImple()
    {
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


       // 테스트용 더미 user 메모리db생성
        userDB.add(new UserDTO(1,"홍성현",26,"01092059813","탑건"));


        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalDate currentDate = startDate.plusDays(i);

            for (int j = 1; j < 5; j++) {
                cinema.add(new CinemaDTO(j, currentDate.atStartOfDay().plusHours(3 * j), seat, movies.get(i)));
            }
        }
    }




    @Getter
    public ArrayList<CinemaDTO> cinema = new ArrayList<>();

    @Getter
    public  ArrayList<MovieDTO> movies = new ArrayList<>();

    @Getter
    public  ArrayList<UserDTO> userDB = new ArrayList<>();

    @Getter
    public  ArrayList<ReservationDTO> reservationDB = new ArrayList<>();


    @Override
    public ArrayList<MovieDTO> getMovie() {
        return movies;
    }

    @Override
    public ArrayList<ReservationDTO> getReservation() {
        return reservationDB;
    }




    @Override
    public ArrayList<ReservationDTO> setReservation(ReservationDTO reservationDTO) {
        ArrayList<ReservationDTO> reservationList = new ArrayList<>();

        reservationDTO.setId(reservationDB.stream().count());
        reservationDB.add(reservationDTO);


        try {
            reservationDB.stream()
                    .filter(m -> m.getUserId() == reservationDTO.getUserId())
                    .forEach(reservationList::add);
        } catch (Exception e) {
            System.err.println("Error occurred while filtering reservations: " + e.getMessage());
            e.printStackTrace();
        }

        return reservationList;
    }

    @Override
    public ArrayList<UserDTO> getUser() {
        return userDB;
    }
}
