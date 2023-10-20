package com.example.webfluxpractice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.webfluxpractice.repository.Post;
import com.example.webfluxpractice.repository.PostR2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PostServiceV2 {
    private final PostR2dbcRepository postR2dbcRepository;

    public Mono<Post> create(Long userId, String title, String content) {
        return postR2dbcRepository.save(Post.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build());
    }

    public Flux<Post> findAll() {
        return postR2dbcRepository.findAll();
    }
    public Mono<Post> findById(Long id) {
        return postR2dbcRepository.findById(id);
    }

    public Flux<Post> findAllByUserId(Long id) {
        return postR2dbcRepository.findAllByUserId(id);
    }

    public Mono<Void> deleteById(Long id) {
        return postR2dbcRepository.deleteById(id);
    }
}
