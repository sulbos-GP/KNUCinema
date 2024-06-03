package com.example.KNUCinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        //model.addAttribute("theaterid", movieReservation.selectTheater();)



    @RequestMapping("/Seat/{id}")
    public String seat(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("Movie",movieService.find(id));
        model.addAttribute("Cinema",movieService.findCinemaDTO(id));
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

    //Seat/1 주소에서 Feach API POST
    @PostMapping("/Seat/1")
    public ResponseEntity<String> reserveSeats(@RequestBody List<CinemaDTO.Seat> seats) {
        // 좌석 데이터를 처리하는 로직
        // 예: 데이터베이스에 저장하거나 비즈니스 로직 수행
        // System.out.println(seats);
        int[][] seatArray = movieService.findCinemaDTO(1).getSeat().getSeat();
        for(int i=0;i<seats.size();i++)
        {
            CinemaDTO.Seat index = seats.get(i);
            seatArray[index.getRow()][index.getCol()] = 1;
        }
        movieService.findCinemaDTO(1).getSeat().setSeat(seatArray);
        System.out.println(movieService.findCinemaDTO(1).getSeat());
        return ResponseEntity.ok("Seats successfully");
    }


    @RequestMapping("/reservation")
    public String reservation(){
        return "reservation";
    }



}
