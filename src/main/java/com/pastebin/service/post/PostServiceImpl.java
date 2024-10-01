package com.pastebin.service.post;

import com.pastebin.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pastebin.model.dto.post.PostCreateDto;
import com.pastebin.model.dto.post.PostDto;
import com.pastebin.model.dto.post.PostUpdateDto;
import com.pastebin.model.entity.Post;
import com.pastebin.exception.NotFoundException;
import com.pastebin.model.entity.PostResponse;
import com.pastebin.repository.PostRepository;
import com.pastebin.service.s3.AmazonS3Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AmazonS3Service s3Service;

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(String.format("Post with id %s not found", postId)));

        String postContent = s3Service.getContent(post.getContentId());

        PostResponse postResponse = postMapper.toPostResponse(post);
        postResponse.setContent(postContent);

        return postResponse;
    }

    @Override
    @Transactional
    public PostDto create(PostCreateDto postCreateDto) {
        Post post = postMapper.toEntity(postCreateDto);

        String contentId = s3Service.uploadContent(postCreateDto.getContent());
        post.setContentId(contentId);

        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public PostDto update(Long id, PostUpdateDto postUpdateDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Post with id %s not found", id)));

        s3Service.deleteContent(post.getContentId());

        String newContentId = s3Service.uploadContent(postUpdateDto.getContent());
        post.setContentId(newContentId);

        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Post with id %s not found", id)));

        s3Service.deleteContent(post.getContentId());

        postRepository.deleteById(id);
    }

    @Override
    public List<PostDto> findAllAuthorPosts(Long userId) {
        return postRepository.findAllByAuthorId(userId).stream()
                .map(postMapper::toDto)
                .toList();
    }
}

