package com.groupC.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tweetId;

    private long postedUserId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
    private long createdUserId;

    @Column(name = "content",nullable = false,length = 200)
    private String text;

//    private Date tweetedDate;

    @CreatedDate
    private Date createdDate;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int numberOfLikes;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int numberOfRetweets;


    @OneToMany(mappedBy = "tweetId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "tweetId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "tweetId",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "tweetId", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Hashtagpost> hashtagposts = new ArrayList<>();

    @OneToMany(mappedBy = "tweetId", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public long incrementLikeCount() {
        return ++numberOfLikes;
    }

    public long decrementLikeCount() {
        return (numberOfLikes < 1) ? 0 : --numberOfLikes;
    }

    public long incrementRepostCount() {
        return ++numberOfRetweets;
    }

    public long decrementRepostCount() {
        return (numberOfRetweets < 1) ? 0 : --numberOfRetweets;
    }



}
