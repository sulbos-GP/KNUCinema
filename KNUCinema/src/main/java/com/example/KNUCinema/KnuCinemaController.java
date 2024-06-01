package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KnuCinemaController {

    @Autowired
    private KnuMovieService movieService;

    @Autowired
    private KnuMovieReservation movieReservation;

    @RequestMapping("/")
    public  String home() {
        return "mainPage";
    }

    @RequestMapping("/Test")
    public String test(){
        return "Test";
    }


    @RequestMapping("/reserve")
    public String reservePage(Model model){
        model.addAttribute("theaterid", movieReservation.selectTheater();)

        return "reserve"; }


    @RequestMapping("/Seat/{id}")
    public String seat(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("Movie",movieService.find(id));
        return "Seat";
    }

    @RequestMapping("/checkbook")
    public  String inputPage(){
        return "inputPhoneNumberForm";
    }
    @GetMapping("/mybook")
    public String checkBook(@RequestParam("phoneNumber") String number, Model model){
        model.addAttribute("phoneNumber",movieService.findPhoneNumber(number));
        return "checkBook";
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
