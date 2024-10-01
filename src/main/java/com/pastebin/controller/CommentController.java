package com.pastebin.controller;

import com.pastebin.config.context.UserContext;
import com.pastebin.model.dto.comment.CommentDto;
import com.pastebin.model.dto.comment.CommentToCreateDto;
import com.pastebin.model.dto.comment.CommentToUpdateDto;
import com.pastebin.service.comment.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserContext userContext;

    @PostMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createComment(@PathVariable("postId") long postId,
                                    @RequestBody @Valid CommentToCreateDto commentDto) {
        long userId = userContext.getUserId();
        return commentService.createComment(postId, userId, commentDto);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllPostComments(@PathVariable("postId") long postId) {
        return commentService.getAllPostComments(postId);
    }

    @PutMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto updateComment(@PathVariable("commentId") long commentId,
                                    @RequestBody @Valid CommentToUpdateDto commentDto) {
        long userId = userContext.getUserId();
        return commentService.updateComment(commentId, userId, commentDto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable("commentId") long commentId) {
        long userId = userContext.getUserId();
        commentService.deleteComment(commentId, userId);
    }
}
