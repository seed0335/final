package com.hello.post.repository;

import com.hello.post.entity.Post;
import com.hello.post.entity.PostLike;
import com.hello.post.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByUserAndPost(User user, Post post);
}
