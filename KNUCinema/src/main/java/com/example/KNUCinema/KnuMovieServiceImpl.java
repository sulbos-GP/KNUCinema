package com.example.KNUCinema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class KnuMovieServiceImpl implements KnuMovieService {
    public  ArrayList<CinemaDTO> movie = new ArrayList<>();
    public ArrayList<MovieDTO> db = new ArrayList<>();
    public ArrayList<UserDTO> userDB = new ArrayList<>();


    public KnuMovieServiceImpl()
    {
        System.out.println("객체 생성");
        int[][] seat =  new int[10][10];
        for (int[] ints : seat) Arrays.fill(ints,0);

        db.add(new MovieDTO(0, "아바타", "/image/Avatar.png", "영화 내용 10"));
        db.add(new MovieDTO(1, "탑건", "/image/TopGun.png", "영화 내용 1"));
        db.add(new MovieDTO(2, "인셉션", "/image/Inception.png", "영화 내용 2"));
        db.add(new MovieDTO(3, "인터스텔라", "/image/Interstellar.png", "영화 내용 3"));
        db.add(new MovieDTO(4, "어벤져스", "/image/Avengers.png", "영화 내용 4"));
        db.add(new MovieDTO(5, "기생충", "/image/Parasite.png", "영화 내용 5"));
        db.add(new MovieDTO(6, "라라랜드", "/image/LaLaLand.png", "영화 내용 6"));
        db.add(new MovieDTO(7, "알라딘", "/image/Aladdin.png", "영화 내용 7"));
        db.add(new MovieDTO(8, "조커", "/image/Joker.png", "영화 내용 8"));
        db.add(new MovieDTO(9, "토이 스토리", "/image/ToyStory.png", "영화 내용 9"));


        //user 메모리db생성
        userDB.add(new UserDTO(1,"홍성현",26,"01092059813","탑건"));

        

        LocalDate startDate = LocalDate.now();
        for (int i = 0; i < 10; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            movie.add(new CinemaDTO(1, currentDate.atStartOfDay(), seat, db.get(i)));
        }
        
    }

    @Override
    public MovieDTO find(int id)
    {
        MovieDTO find = db.stream().filter(m-> m.getId()==id).findFirst().get();
        return find;
    }

    @Override
    public CinemaDTO findCinemaDTO(int id)
    {
        CinemaDTO find = movie.stream().filter(m->m.getId()==id).findFirst().get();
        return find;
    }

    
    //휴대폰 번호로 예약 조회
    public UserDTO findPhoneNumber(String phoneNumber){

        UserDTO findNumber = userDB.stream().filter(m->m.getPhoneNumber().equals(phoneNumber)).findFirst().get();
        return findNumber;
    }


    @Override
    public int count()
    {
        System.out.println(db.size());
        return db.size();
    }
}
