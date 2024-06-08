package com.example.KNUCinema;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GPTController {

    private final ChatGptService chatGptService;

    public GPTController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @PostMapping("/question")
    public ChatGptResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto) {
        System.out.println(requestDto.getClass());
        return chatGptService.askQuestion(requestDto);
    }
}