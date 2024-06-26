package com.example.KNUCinema;


import lombok.*;

import java.util.List;

@Getter@Setter@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String name;
    private int age;
    private String phoneNumber;
}
