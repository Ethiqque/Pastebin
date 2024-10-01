package com.pastebin.controller;

import com.pastebin.config.context.UserContext;
import com.pastebin.model.dto.like.LikeDto;
import com.pastebin.service.like.LikeService;
import jakarta.validation.constraints.Positive;
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
    public LikeDto likePost(@Positive @PathVariable long postId) {
        long userId = userContext.getUserId();
        return likeService.addLikeOnPost(userId, postId);
    }

    @DeleteMapping("/post/{postId}/{likeId}")
    public void deleteLikeFromPost(@Positive @PathVariable long postId,
                                   @Positive @PathVariable long likeId) {
        long userId = userContext.getUserId();
        likeService.removeLikeFromPost(likeId, userId, postId);
    }

    @PostMapping("/comment/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeDto likeComment(@Positive @PathVariable long commentId) {
        long userId = userContext.getUserId();
        return likeService.addLikeOnComment(userId, commentId);
    }

    @DeleteMapping("/comment/{commentId}/{likeId}")
    public void deleteLikeFromComment(@Positive @PathVariable long commentId,
                                      @Positive @PathVariable long likeId) {
        long userId = userContext.getUserId();
        likeService.removeLikeFromComment(likeId, userId, commentId);
    }
}