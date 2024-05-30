package com.example.KNUCinema;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Service
public class KnuMovieServiceImpl implements KnuMovieService {

    private ArrayList<MovieDTO> db = new ArrayList<>();

    public KnuMovieServiceImpl()
    {
        System.out.println("객체 생성");
        boolean[][] seat =  new boolean[6][10];
        for (boolean[] booleans : seat) Arrays.fill(booleans, false);
        db.add(new MovieDTO(1,"탑 건","/image/TopGun.png","영화 내용",seat));

    }

    public MovieDTO find(int id)
    {
        MovieDTO find = db.stream().filter(m-> m.getId()==id).findFirst().get();
        return find;
    }

    public int count()
    {
        return db.size();
    }
}
