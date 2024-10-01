package com.pastebin.repository;

import com.pastebin.model.entity.Like;
import com.pastebin.model.entity.Post;
import com.pastebin.model.entity.url.Hash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserIdAndPostId(Long userId, Long postId);
    Optional<Like> findByUserIdAndCommentId(Long userId, Long likeId);
}
