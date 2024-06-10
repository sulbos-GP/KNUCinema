package com.example.KNUCinema;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class GPTController {
    @Autowired
    private DatabaseDAO DB;

    @Autowired
    private ChatGptService chatGptService;

    public GPTController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        String movieList ="";
        for(int i=0;i<DB.getMovie().size();i++)
        {
            movieList+=DB.getMovie().get(i).getTitle()+"/";
        }
        requestDto.setQuestion(new ChatGptRequestDto.Message("user","영화 리스트를 /로 나눠서 알려줄꺼야." +movieList+
                "너는 지금부터 영화 추천 도우미야."+"너는 지금부터 내가 준 영화제목 중에서 3가지로 선택해. "+
                "반드시 내가 말하는 유의사항을 지켜서 대답하도록해."+
                "1. 답변 양식은 예를 들어 너가 '탑건','인터스텔라','슈퍼맨'으로 결정했다면 탑건/인터스텔라/슈퍼맨 으로 단답해."+
                "2. 반드시 내가 예시로 들어준 답변 형식으로만 답변할 것."+
                "3. 내가 처음에 알려준 영화 리스트 내에서 결정할 것."+
                "4. 영화 순서는 섞어도 될 것."+
                "위 사항을 지켜서 지금 나에게 영화를 추천해 줘"));

        ChatGptResponseDto gpt = chatGptService.askQuestion(requestDto);
        System.out.println(gpt.getChoices().getLast().getMessage().getContent());
        return chatGptService.askQuestion(requestDto);
    }
}