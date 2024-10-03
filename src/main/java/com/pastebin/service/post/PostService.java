package com.pastebin.service.post;

import com.pastebin.model.dto.post.PostCreateDto;
import com.pastebin.model.dto.post.PostDto;
import com.pastebin.model.dto.post.PostUpdateDto;
import com.pastebin.model.dto.post.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse getPostById(Long postId);

    PostResponse getPostByUrl(String url);

    PostDto create(PostCreateDto postCreateDto);

    PostDto update(Long id, PostUpdateDto postUpdateDto);

    void deleteById(Long id);

    List<PostDto> findAllAuthorPosts(Long userId);
}
