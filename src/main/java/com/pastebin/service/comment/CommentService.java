package com.pastebin.service.comment;


import com.pastebin.model.dto.comment.CommentDto;
import com.pastebin.model.dto.comment.CommentToCreateDto;
import com.pastebin.model.dto.comment.CommentToUpdateDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, Long userId, CommentToCreateDto commentDto);

    List<CommentDto> getAllPostComments(Long postId);

    CommentDto updateComment(Long commentId, Long userId, CommentToUpdateDto commentDto);

    void deleteComment(Long commentId, Long userId);

    CommentDto getById(Long commentId);
}
