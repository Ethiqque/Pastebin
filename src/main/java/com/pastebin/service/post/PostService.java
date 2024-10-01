package com.pastebin.service.post;

import com.pastebin.model.dto.post.PostCreateDto;
import com.pastebin.model.dto.post.PostDto;
import com.pastebin.model.dto.post.PostUpdateDto;
import com.pastebin.model.entity.PostResponse;
import com.pastebin.model.entity.url.Url;

import java.util.List;

public interface PostService {

    PostResponse getPostById(Long id);

    PostDto create(PostCreateDto postCreateDto);

    PostDto update(Long id, PostUpdateDto postUpdateDto);

    void deleteById(Long id);

    List<PostDto> findAllAuthorPosts(Long userId);
}
