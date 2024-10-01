package com.pastebin.service.comment;

import com.pastebin.exception.NotFoundException;
import com.pastebin.mapper.CommentMapper;
import com.pastebin.model.dto.comment.CommentDto;
import com.pastebin.model.dto.comment.CommentToCreateDto;
import com.pastebin.model.entity.Comment;
import com.pastebin.model.entity.Post;
import com.pastebin.repository.CommentRepository;
import com.pastebin.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final NewCommentProducer newCommentPublisher;

    @Override
    public CommentDto createComment(long postId, long userId, CommentToCreateDto commentDto) {

        Post post = getById(postId);
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setAuthorId(userId);
        comment.setPost(post);

        comment = commentRepository.save(comment);
        log.info("Created comment on post {} authored by {}", postId, userId);

        CommentDto dto = commentMapper.toDto(comment);
        newCommentPublisher.publish(new NewCommentEvent(dto));

        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllPostComments(long postId) {

        return commentRepository.findAllByPostId(postId).stream()
                .sorted(Comparator.comparing(Comment::getCreatedAt))
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto updateComment(long commentId, long userId, CommentToUpdateDto updatedCommentDto) {

        Comment commentToUpdate = commonServiceMethods.findEntityById(commentRepository, commentId, "Comment");

        commentValidator.validateUpdateAlbum(commentToUpdate, userId);

        commentMapper.update(updatedCommentDto, commentToUpdate);
        log.info("Updated comment {} on post {} authored by {}", commentId, commentToUpdate.getPost().getId(), userId);
        commentRepository.save(commentToUpdate);
        return commentMapper.toDto(commentToUpdate);
    }

    @Override
    public CommentDto deleteComment(long postId, long commentId, long userId) {

        Comment comment = commonServiceMethods.findEntityById(commentRepository, commentId, "Comment");
        CommentDto commentToDelete = commentMapper.toDto(comment);

        commentValidator.validateDeleteAlbum(postId, userId, comment);

        commentRepository.deleteById(commentId);
        log.info("Deleted comment {} on post {} authored by {}", commentId, comment.getPost().getId(), userId);
        return commentToDelete;
    }

    @Override
    public CommentDto getById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(String.format("Comment with id %d not found", commentId)));
        return commentMapper.toDto(comment);
    }
}