package com.example.KNUCinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//Choice
@Getter
@NoArgsConstructor
public class Choice implements Serializable {

    private String text;
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;
    private Message message;

    @Builder
    public Choice(String text, Integer index, String finishReason, Message message) {
        this.text = text;
        this.index = index;
        this.finishReason = finishReason;
        this.message = message;
    }

    @Getter
    @NoArgsConstructor
    public static class Message implements Serializable {
        private String role;
        private String content;

        @Builder
        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }
}
