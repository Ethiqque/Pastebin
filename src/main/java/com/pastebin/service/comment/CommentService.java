package com.pastebin.service.comment;


import com.pastebin.model.dto.comment.CommentDto;
import com.pastebin.model.dto.comment.CommentToCreateDto;
import com.pastebin.model.dto.comment.CommentToUpdateDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, long userId, CommentToCreateDto commentDto);

    List<CommentDto> getAllPostComments(long postId);

    CommentDto updateComment(long commentId, long userId, CommentToUpdateDto commentDto);

    CommentDto deleteComment(long postId, long commentId, long userId);

    CommentDto getById(Long commentId);
}
