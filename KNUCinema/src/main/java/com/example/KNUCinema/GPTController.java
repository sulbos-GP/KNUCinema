package com.example.KNUCinema;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class GPTController {
    @Autowired
    private DatabaseDAO DB;

    @Autowired
    private final ChatGptService chatGptService;

    public GPTController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/question")
    public String sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        String movieList ="";
        for(int i=0;i<DB.getMovie().size();i++)
        {
            movieList+=DB.getMovie().get(i).getTitle()+"/";
        }
        requestDto.setQuestion(new ChatGptRequestDto.Message("user",movieList+"영화 리스트를 /로 나눠서 알려줄꺼야." +
                "너는 지금부터 내가 준 영화제목 중에서 3가지로 정해서 나에게 알려줘야 해.답변 양식은 예를 들어 '탑건/인터스텔라/슈퍼맨' 처럼 나에게 보내줘야 해." +
                "너는 반드시 이 양식을 지켜야 해. 지금부터 나에게 영화 3가지를 정해서 알려줘."));

        ChatGptResponseDto gpt = chatGptService.askQuestion(requestDto);
        System.out.println(gpt.getChoices().getLast().getMessage().getContent());
        return "minaPage";
    }
}