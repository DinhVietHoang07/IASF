package com.fpt.hellospring.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student {
    private String rollNumber;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private LocalDateTime dob;
    private int status;
}
