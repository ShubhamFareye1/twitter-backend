package com.groupC.twitter.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TweetDto {

    private long tweetId;

    private long postedUser;

    private long createdUser;

    private String text;

    private Date tweetedDate;

    private Date createdDate;

    private int numberOFLikes;

    private int numberOFTweets;



}
