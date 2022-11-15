package com.groupC.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    private long createdUserId;

    @ManyToOne
    @JoinColumn(updatable = false,nullable = false)
    private User createdUser;

    @ManyToOne
    @JoinColumn(nullable = true)
    private User postedUser;

    @Column(name = "content",nullable = false,length = 200)
    private String text;

//  private Date tweetedDate;

    @CreationTimestamp
    private Date createdDate;
    @Column(columnDefinition = "integer default 0")
    private int numberOfLikes;

    @Column(columnDefinition = "integer default 0")
    private int numberOfComments;

    @Column(columnDefinition = "integer default 0")
    private int numberOfRetweets;

    private String image;


    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "tweet",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Hashtagpost> hashtagposts = new ArrayList<>();

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

    public long incrementCommentCount() {
        return ++numberOfComments;
    }
    public long decrementCommentCount() {
        return (numberOfComments < 1) ? 0 : --numberOfComments;
    }

    @ElementCollection
    private Map<String, Date> hashtags = new HashMap<>();

    @ElementCollection
    private Map<String, Date> mentions = new HashMap<>();




}
