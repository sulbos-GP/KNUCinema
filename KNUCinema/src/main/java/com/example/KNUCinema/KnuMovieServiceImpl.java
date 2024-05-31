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
        int[][] seat =  new int[10][10];
        for (int[] ints : seat) Arrays.fill(ints,0);
        db.add(new MovieDTO(1,"탑 건","/image/TopGun.png","영화 내용",seat));

    }

    @Override
    public MovieDTO find(int id)
    {
        MovieDTO find = db.stream().filter(m-> m.getId()==id).findFirst().get();
        return find;
    }


    @Override
    public int count()
    {
        System.out.println(db.size());
        return db.size();
    }
}
