package com.groupC.twitter.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "hashtagposts")
public class Hashtagpost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long hashtagpostId;

    private long hashtagId;

    private long tweetId;

    @ManyToOne(targetEntity = Hashtag.class)
    @JoinColumn(updatable = false,nullable = false)
    private Hashtag hashtag;

    @ManyToOne(targetEntity = Tweet.class)
    @JoinColumn(updatable = false,nullable = false)
    private Tweet tweet;

}
