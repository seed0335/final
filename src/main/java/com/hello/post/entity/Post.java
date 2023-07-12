package com.hello.post.entity;

import jakarta.persistence.*;

@Entity
public class Post extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Like like;

    //단방향
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Post() {
    }
}
