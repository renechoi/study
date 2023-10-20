package com.example.webfluxpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String name;
    private String email;
}
