package com.groupC.twitter.model;

import lombok.Data;

import java.util.Date;

@Data
public class Tweet {

    private long tweetId;

    private long postedUser;

    private long createdUser;

    private String text;

//    private Date tweetedDate;

    private Date createdDate;

    private int numberOFLikes;

    private int numberOFTreweets;



}
