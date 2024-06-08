package com.example.KNUCinema;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//Service
@Service
public class ChatGptService {

    private static RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(GPTConfig.MEDIA_TYPE));
        headers.add(GPTConfig.AUTHORIZATION, GPTConfig.BEARER + GPTConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                GPTConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
      return this.getResponse(
                this.buildHttpEntity(
                        new ChatGptRequestDto(
                                GPTConfig.MODEL,
                                requestDto.getQuestion(),
                                GPTConfig.MAX_TOKEN,
                                GPTConfig.TEMPERATURE,
                                GPTConfig.TOP_P
                        )
                )
        );
    }
}