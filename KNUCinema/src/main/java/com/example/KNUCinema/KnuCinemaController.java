package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KnuCinemaController {

    @Autowired
    private KnuMovieService movieService;

    @RequestMapping("/")
    public  String home() {
        return "home";
    }

    @RequestMapping("/count")
    public String count()
    {
        return "카운트 = "+movieService.count();
    }


}
