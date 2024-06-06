package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnuMovieServiceImpl implements KnuMovieService {

    @Autowired
    private DatabaseDAO DB;


    public KnuMovieServiceImpl()
    {

        
    }

    @Override
    public MovieDTO find(int id)
    {
        MovieDTO find = DB.getMovie().stream().filter(m-> m.getId()==id).findFirst().get();
        return find;
    }

    @Override
    public CinemaDTO findCinemaDTO(int id)
    {
        CinemaDTO find = DB.getCinema().stream().filter(m->m.getId()==id).findFirst().get();
        return find;
    }

    
    //휴대폰 번호로 예약 조회
    public UserDTO findPhoneNumber(String phoneNumber){

        UserDTO findNumber = DB.getUser().stream().filter(m->m.getPhoneNumber().equals(phoneNumber)).findFirst().get();
        return findNumber;
    }

    //이름으로 예약 조회 (이름과 휴대폰 번호로 예약 조회해야하기 때문에 필요함)
    public UserDTO findName(String name){
        UserDTO findName = DB.getUser().stream().filter(m->m.getName().equals(name)).findFirst().get();
        return  findName;
    }


    @Override
    public int count()
    {
        System.out.println(DB.getMovie().size());
        return DB.getMovie().size();
    }
}
