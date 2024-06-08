package com.example.KNUCinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller
public class KnuCinemaController {

    @Autowired
    private KnuMovieService movieService; //TODO : KnuMovieService -> IKnuMovieService
    @Autowired
    private KnuMovieReservation movieReservation;  //TODO : KnuMovieReservation -> IKnuMovieReservation
    @Autowired
    private  KnuMovieServiceImpl knuMovieServiceImpl; // 메모리db에 있는 영화 list 불러오기 위함


    @RequestMapping("/")
    public  String home() {
        return "mainPage";
    }

    @RequestMapping("/tests")
    public  String test() {
        return "TEST";
    }
    

    @PostMapping("/Seat/{id}")
    public String seat(@PathVariable("id") int id, Model model ,@RequestParam String selectedValue)
    {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+selectedValue);
        model.addAttribute("Movie",movieService.find(id));
        model.addAttribute("Cinema",movieService.findCinemaDTO(id));
        model.addAttribute("Time",movieService.findCinemaDTO(id).getTime());
        return "Seat";
    }

    //예약 조회 누르면 이름과 휴대폰 번호 입력하는 화면
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

   /* @RequestMapping("/reserve")
    public String reserve(Model model){
        //선택할 수 있는 날짜를 오늘부터 7일 후 까지만 제약을 두기 위해 작성함
        LocalDate today = LocalDate.now();

        for(int i =0; i<10;i++){
            model.addAttribute("movies"+i, knuMovieServiceImpl.db.get(i));     
            model.addAttribute("movieTime"+i, knuMovieServiceImpl.movie.get(i));
        }

        model.addAttribute("limitMinDate", today);
        model.addAttribute("limitMaxDate", today.plusDays(7));

        return "reservePage";
    }*/

    //영화관 아이디 받으면 볼 수 있는 영화 리스트 보내기
    @RequestMapping("/reserve")
    public String reserve(Model model){
        int id = 1;
        LocalDate today = LocalDate.now();



        List<CinemaDTO> allMovies = movieReservation.getAllMovies();

        Map<String, List<CinemaDTO>> groupedByTitle = allMovies.stream()
                .collect(Collectors.groupingBy(CinemaDTO::getTitle));

        ArrayList<CinemaListDTO> list = groupedByTitle.entrySet().stream()
                .map(entry -> {
                    String title = entry.getKey();
                    ArrayList<LocalDateTime> times = entry.getValue().stream()
                            .map(CinemaDTO::getTime)
                            .collect(Collectors.toCollection(ArrayList::new));
                    CinemaDTO representative = entry.getValue().get(0);
                    return new CinemaListDTO(representative, times);
                })
                .collect(Collectors.toCollection(ArrayList::new));

        list.sort(Comparator.comparingInt(CinemaListDTO::getId));

        model.addAttribute("movies", list);
        //model.addAttribute("movies", movieReservation.getAllMovies());
        //model.addAttribute("movieTime",movieReservation.getMoviesByCinemaId(id));




        model.addAttribute("limitMinDate", today);
        model.addAttribute("limitMaxDate", today.plusDays(7));
        return "reservePage";
    }



    @RequestMapping("/userForm")
    public String showForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "GetUserDataPage"; // Thymeleaf 템플릿 이름
    }

    @PostMapping("/userForm")
    public String submitForm(UserDTO user, Model model) {
        try {
            movieReservation.setUserData(user);
        }
        catch (Exception e){

        }
        return "redirect:/reserve"; // 결과를 보여줄 템플릿 이름
    }


    //영화관 선택후 시간과 이름으로 영화 리스트 보내기?
    @RequestMapping("/reserve/movies")
    public String reserveMovie(@RequestParam("time") String time, @RequestParam("title") String title, Model model) {
        //TODO : 뭔가 이상함
        model.addAttribute("Movie",movieReservation.getMoviesByTime(time,title));

        return "reserve";
    }


    //Seat/1 주소에서 Feach API POST
    //0 : 빈좌석 1: 성인 2: 청소년 3:경로 4:장애인
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
