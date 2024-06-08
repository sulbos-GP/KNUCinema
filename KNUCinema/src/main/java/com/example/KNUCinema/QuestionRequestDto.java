package com.example.KNUCinema;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//QuestionRequestDto
@Getter @Setter
public class QuestionRequestDto implements Serializable {
    private ChatGptRequestDto.Message question;
}
