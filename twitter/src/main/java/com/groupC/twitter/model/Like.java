package com.groupC.twitter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long likeId;

    private long tweetId;
    private long userId;
    @ManyToOne(targetEntity = Tweet.class)
    @JoinColumn(updatable = false,nullable = false)
    private Tweet tweet;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
    private User user;


}
