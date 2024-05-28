package com.example.KNUCinema;

import java.util.ArrayList;

public class KnuMovieServiceImpl {

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
