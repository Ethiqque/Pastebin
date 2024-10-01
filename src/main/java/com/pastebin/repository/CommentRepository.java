package com.pastebin.repository;

import com.pastebin.model.entity.Comment;
import com.pastebin.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {}
