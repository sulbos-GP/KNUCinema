package com.example.KNUCinema;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class KnuMovieServiceImpl implements KnuMovieService {
    public   ArrayList<CinemaDTO> movie = new ArrayList<>();
    public ArrayList<MovieDTO> db = new ArrayList<>();
    public ArrayList<UserDTO> userDB = new ArrayList<>();


    public KnuMovieServiceImpl()
    {
        System.out.println("객체 생성");
        int[][] seat =  new int[10][10];
        for (int[] ints : seat) Arrays.fill(ints,0);
   
        db.add(new MovieDTO(1,"탑 건","/image/TopGun.png","영화 내용"));

        //user 메모리db생성
        userDB.add(new UserDTO(1,"홍성현",26,"01092059813","탑건"));

        movie.add(new CinemaDTO(1,new Time(1),seat,db.get(0)));
    }

    @Override
    public MovieDTO find(int id)
    {
        MovieDTO find = db.stream().filter(m-> m.getId()==id).findFirst().get();
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
