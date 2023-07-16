package com.hello.post.repository;

import com.hello.post.entity.Comment;
import com.hello.post.entity.CommentLike;
import com.hello.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
