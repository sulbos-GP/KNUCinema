package com.example.KNUCinema;


import lombok.*;

@Getter@Setter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private int age;
    private String phoneNumber;
    private String movieTitle;
}
