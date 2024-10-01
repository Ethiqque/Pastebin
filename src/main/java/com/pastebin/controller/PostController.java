package com.pastebin.controller;

import com.pastebin.model.dto.post.PostCreateDto;
import com.pastebin.model.dto.post.PostDto;
import com.pastebin.model.dto.post.PostUpdateDto;
import com.pastebin.model.entity.PostResponse;
import com.pastebin.model.entity.url.Url;
import com.pastebin.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long postId) {
        PostResponse postResponse = postService.getPostById(postId);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("user/{userId}")
    public List<PostDto> findAllAuthorPosts(@PathVariable Long userId) {
        return postService.findAllAuthorPosts(userId);
    }

    @PostMapping
    public PostDto create(@RequestBody @Valid PostCreateDto postCreateDto) {
        return postService.create(postCreateDto);
    }

    @PatchMapping("{postId}")
    public PostDto update(
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateDto postUpdateDto
    ) {
        return postService.update(postId, postUpdateDto);
    }

    @DeleteMapping("{postId}")
    public void deleteById(@PathVariable Long postId) {
        postService.deleteById(postId);
    }
}