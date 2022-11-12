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

    @ManyToOne(targetEntity = Hashtag.class)
    @JoinColumn(updatable = false,nullable = false)
    private long hashtagId;

    @ManyToOne(targetEntity = Tweet.class)
    @JoinColumn(updatable = false,nullable = false)
    private long tweetId;

}
