package com.example.KNUCinema;

import lombok.*;

import java.io.Serializable;

//QuestionRequestDto
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class QuestionRequestDto implements Serializable {
    private ChatGptRequestDto.Message question;
}
