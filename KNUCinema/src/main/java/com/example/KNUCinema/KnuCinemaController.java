package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KnuCinemaController {

    @Autowired
    private KnuMovieService movieService;

    @Autowired
    private KnuMovieReservation movieReservation;

    @RequestMapping("/")
    public  String home() {
        return "home";
    }

    @RequestMapping("/Test")
    public String test(){
        return "Test";
    }




    @RequestMapping("/Seat/{id}")
    public String seat(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("Movie",movieService.find(id));
        return "Seat";
    }

    @RequestMapping("/count")
    public String count()
    {
        return "카운트 = "+movieService.count();
    }

    public void adultNum(int adult)
    {
        System.out.println("Adult 추가");
        adult++;
    }





    @RequestMapping("/reservation")
    public String reservation(){
        return "reservation";
    }



}
