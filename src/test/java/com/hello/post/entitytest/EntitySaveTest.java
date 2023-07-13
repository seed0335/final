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
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class EntitySaveTest {

    UserRepository userRepository;
    PostRepository postRepository;
    CommentRepository commentRepository;
    LikeRepository likeRepository;

    @Autowired
    public EntitySaveTest(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }

    @Test
    @Rollback(value = false)
    void save1() {
        //유저 저장 테스트
        User user = new User();
        user.setUsername("seed");
        user.setPassword("0335");
        userRepository.save(user);

        //포스트 저장 테스트
        Post post = new Post();
        post.setTitle("오늘의 할일");
        post.setContent("개인과제를 해야 합니다.");
        post.setUser(user);
        postRepository.save(post);

        //댓글 저장 테스트
        Comment comment = new Comment();
        comment.setComment("화이팅입니다.");
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

        //좋아요 저장
        Like like = new Like();
        like.setLike(1);
        like.setUser(user);
        like.setPost(post);
        likeRepository.save(like);
    }
}
