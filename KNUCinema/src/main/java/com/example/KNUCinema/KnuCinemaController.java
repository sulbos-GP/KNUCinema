package com.example.KNUCinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
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

    @RequestMapping("/payment")
    public  String payment() {
        return "payment";
    }


    @PostMapping("/Seat/{id}")
    public String seat(@PathVariable("id") int id,
                       @RequestParam("selectedValue") String selectedValue,
                       @RequestParam("user") String user,
                       @RequestParam("date") String date,
                       @RequestParam("movieId") int movieID, Model model)
    {

        int movieId = movieID;
        String number = user;
        String t = selectedValue;
        String dates = date+" "+t;
        Pattern pattern = Pattern.compile("(\\d{4}년 \\d{2}월 \\d{2}일)\\s.+\\s(\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(dates);

        LocalDateTime localDateTime = null;
        if (matcher.find()) {
            String datePart = matcher.group(1); // 날짜 부분
            String timePart = matcher.group(2); // 시간 부분
            String dateTimeString = datePart + " " + timePart; // 날짜와 시간 부분을 결합

            // DateTimeFormatter 정의
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

            // 문자열을 LocalDateTime으로 파싱
            localDateTime = LocalDateTime.parse(dateTimeString, formatter);
        } else {
            System.err.println("입력 문자열을 파싱할 수 없습니다.");
        }

        CinemaDTO cinemaDTO = movieService.findTimeCinemaDTO(id,localDateTime);


        model.addAttribute("Movie",movieService.find(id));
        model.addAttribute("number",user);
        model.addAttribute("selectedValue",selectedValue);
        model.addAttribute("Cinema",cinemaDTO);
        model.addAttribute("Time",cinemaDTO.getTime());
        model.addAttribute("movieID",movieId);


        return "Seat";
    }

    //예약 조회 누르면 이름과 휴대폰 번호 입력하는 화면
    @RequestMapping("/checkbook")
    public  String inputPage(){
        return "inputPhoneNumberForm";
    }


    @GetMapping("/mybook")
    public String checkBook(@RequestParam("phoneNumber") String number, Model model){

        UserDTO user = movieService.findUser(number);
        model.addAttribute("UserDatas",user);

        ArrayList<ReservationDTO> reservations = movieReservation.getReservationById(user.getId());
        model.addAttribute("ReservationDatas", reservations);
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


    /*@PostMapping("/reserve")
    public String submitReserve(@RequestParam("selectedValue") String selectedValue, Model model) {
        int id;

        try {
            selectedValue

            redirectAttribute.addFlashAttribute("user", user.getPhoneNumber());
        }
        catch (Exception e){

        }
        return "redirect:/Seat/" + String.valueOf(id); // 결과를 보여줄 템플릿 이름
    }*/

    //영화관 아이디 받으면 볼 수 있는 영화 리스트 보내기
    @RequestMapping("/reserve")
    public String reserve(@ModelAttribute("user") String user, Model model){
        int id = 1;
        LocalDate today = LocalDate.now();

        model.addAttribute("number", user);


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
    public String submitForm(UserDTO user, RedirectAttributes redirectAttribute, Model model) {
        try {
            movieReservation.setUserData(user);
            redirectAttribute.addFlashAttribute("user", user.getPhoneNumber());

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
    @PostMapping("/Seat/Post")
    public ResponseEntity<Map<String,String>> reserveSeats(@RequestBody Map<String,Object> request){
        //List<CinemaDTO.Seat> seats,@RequestParam("number") String number)
        // 좌석 데이터를 처리하는 로직
        // 예: 데이터베이스에 저장하거나 비즈니스 로직 수행
        Map<String, String> response = new HashMap<>();
        try {
            String number = (String) request.get("number");
            int movieID = (int) request.get("movieID");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String timeString = (String) request.get("time");
            LocalDateTime time = LocalDateTime.parse(timeString, formatter);

            ObjectMapper mapper = new ObjectMapper();
            List<CinemaDTO.Seat> seats = mapper.convertValue(request.get("selectedSeats"), new TypeReference<List<CinemaDTO.Seat>>() {
            });
            String reserSeat = "";

            int[][] seatArray = movieService.findTimeCinemaDTO(movieID, time).getSeat().getSeat();
            for (int i = 0; i < seats.size(); i++) {

                CinemaDTO.Seat index = seats.get(i);
                if (seatArray[index.getRow()][index.getCol()] != 2) {
                    seatArray[index.getRow()][index.getCol()] = 2;
                    reserSeat += (char) ('A' + index.getRow()) + "" + index.getCol() + " ";
                }
            }
            System.out.println(reserSeat);

            //ReservationDTO reservationDTO = new ReservationDTO(1,cinemaDTO, seat, movies.get(1)), 1);

            movieService.findCinemaDTO(movieID).getSeat().setSeat(seatArray);

            UserDTO userDTO = movieService.findUser(number);
            movieReservation.setReservation(movieService.findCinemaDTO(movieID), userDTO.getId(), reserSeat);


            System.out.println(movieService.findCinemaDTO(movieID).getSeat());
            response.put("status", "success");
            response.put("redirectUrl", "/payment");
        } catch (Exception e){
            response.put("status", "Fail");
        }



        return ResponseEntity.ok(response);



    }

    @Autowired
    private DatabaseDAO DB;

    @Autowired
    private ChatGptService chatGptService;

    public void GPTController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @RequestMapping("/question")
    public String sendQuestion(QuestionRequestDto requestDto, Model model) {
        String movieList ="";
        for(int i=0;i<DB.getMovie().size();i++)
        {
            movieList+=DB.getMovie().get(i).getTitle()+"/";
        }
        requestDto.setQuestion(new ChatGptRequestDto.Message("user","영화 리스트를 /로 나눠서 알려줄꺼야." +movieList+
                "너는 지금부터 영화 추천 도우미야."+"너는 지금부터 내가 준 영화제목 중에서 3개만 선택해. "+
                "반드시 내가 말하는 유의사항을 지켜서 대답하도록해."+
                "1. 출력 형식은 예를 들어 너가 '탑건','인터스텔라','슈퍼맨'으로 결정했다면 탑건/인터스텔라/슈퍼맨 으로 출력해."+
                "2. 반드시 내가 예시로 들어준 출력 형식으로만 출력할 것."+
                "3. 내가 처음에 알려준 영화 리스트 내에서 결정할 것."+
                "4. 영화 순서는 섞어도 될 것."+
                "위 사항을 지켜서 지금 나에게 영화를 추천해 줘"));

        ChatGptResponseDto gpt = chatGptService.askQuestion(requestDto);
        System.out.println(gpt.getChoices().getLast().getMessage().getContent());

        String gptAnswer = gpt.getChoices().getLast().getMessage().getContent();
        String[] parts = gptAnswer.split("/");

        List<String> answersList = new ArrayList<>();
        for(String part : parts){
            answersList.add(part);
        }

        model.addAttribute("answers", answersList);
        return "recommendPage";
    }





    @RequestMapping("/reservation")
    public String reservation(){
        return "reservation";
    }



}
