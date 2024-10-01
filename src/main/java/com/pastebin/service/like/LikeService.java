package com.pastebin.service.like;

import com.pastebin.model.dto.like.LikeDto;

public interface LikeService {

    LikeDto addLikeOnPost(long userId, long postId);

    LikeDto addLikeOnComment(long userId, long commentId);

    void removeLikeFromPost(long userId, long postId);

    void removeLikeFromComment(long userId, long postId);
}
