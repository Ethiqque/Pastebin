package com.pastebin.repository;

import com.pastebin.model.entity.Post;
import com.pastebin.model.entity.url.Hash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);

    Optional<Post> findPostByUrl(String url);
}
