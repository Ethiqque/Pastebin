package com.pastebin.controller;

import com.pastebin.config.context.UserContext;
import com.pastebin.model.dto.like.LikeDto;
import com.pastebin.service.like.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final UserContext userContext;

    @PostMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeDto likePost(@PathVariable long postId) {
        long userId = userContext.getUserId();
        return likeService.addLikeOnPost(userId, postId);
    }

    @DeleteMapping("/post/{postId}/{likeId}")
    public void deleteLikeFromPost(@PathVariable long postId) {
        long userId = userContext.getUserId();
        likeService.removeLikeFromPost(userId, postId);
    }

    @PostMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeDto likeComment(@PathVariable long commentId) {
        long userId = userContext.getUserId();
        return likeService.addLikeOnComment(userId, commentId);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteLikeFromComment(@PathVariable long commentId) {
        long userId = userContext.getUserId();
        likeService.removeLikeFromComment(userId, commentId);
    }
}