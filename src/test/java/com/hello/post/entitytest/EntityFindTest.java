package com.hello.post.entitytest;

import com.hello.post.entity.Comment;
import com.hello.post.entity.Like;
import com.hello.post.entity.Post;
import com.hello.post.entity.User;
import com.hello.post.repository.CommentRepository;
import com.hello.post.repository.LikeRepository;
import com.hello.post.repository.PostRepository;
import com.hello.post.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityFindTest {

    UserRepository userRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;

    @Autowired
    public EntityFindTest(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Test
    void find() {
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        Post post = postRepository.findById(1L).orElse(null);
        System.out.println(post.getId());
        System.out.println(post.getCreatedAt());
        System.out.println(post.getModifiedAt());

        User user1 = post.getUser();

        Comment comment = commentRepository.findById(1L).orElse(null);
        Post post1 = comment.getPost();
        System.out.println(post1.getTitle());

        Like like = likeRepository.findById(1L).orElse(null);
        Integer like1 = like.getLike();
        System.out.println("like1 = " + like1);
    }
}
