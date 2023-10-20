package com.example.webfluxpractice.dto;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long userId;
    private String title;
    private String content;
}
