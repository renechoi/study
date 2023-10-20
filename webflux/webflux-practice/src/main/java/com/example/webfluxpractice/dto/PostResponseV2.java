package com.example.webfluxpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.webfluxpractice.repository.Post;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseV2 {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostResponseV2 of(Post post) {
        return PostResponseV2.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
