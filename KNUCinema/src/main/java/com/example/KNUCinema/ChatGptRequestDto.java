package com.example.KNUCinema;

//DTO

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//ChatGptRequestDto
@Getter
@NoArgsConstructor
public class ChatGptRequestDto implements Serializable {

    private String model;
    @JsonProperty
    private List<Message> messages;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    private Double temperature;
    @JsonProperty("top_p")
    private Double topP;

    @Builder
    public ChatGptRequestDto(String model, Message messages,
                             Integer maxTokens, Double temperature,
                             Double topP) {
        this.model = model;
        this.messages = List.of(messages);
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.topP = topP;
    }
    @Getter @Setter
    public static class Message{
        @JsonProperty("role")
        private String role;

        @JsonProperty("content")
        private String content;

        public Message(String role,String content)
        {
            this.role=role;
            this.content=content;
        }
    }
}


