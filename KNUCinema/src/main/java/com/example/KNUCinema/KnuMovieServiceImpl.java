package com.example.KNUCinema;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KnuMovieServiceImpl implements KnuMovieService {

    private ArrayList<MovieDTO> db = new ArrayList<>();

    public KnuMovieServiceImpl()
    {
        System.out.println("객체 생성");
        db.add(new MovieDTO("탑 건","/image/TopGun.png","영화 내용",new boolean[10][6]));
    }

    public int count()
    {
        return db.size();
    }
}
