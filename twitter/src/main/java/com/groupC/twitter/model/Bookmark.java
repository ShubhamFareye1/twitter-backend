package com.groupC.twitter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookmarkId;
    private long userId;
    private long tweetId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
    private User user;

    @ManyToOne(targetEntity = Tweet.class)
    @JoinColumn(updatable = false,nullable = false)
    private Tweet tweet;
}
