package com.groupC.twitter.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Tweet {

    private long tweetId;

    private long postedUser;

    private long createdUser;

    private String text;

//  private Date tweetedDate;

    private Date createdDate;

    private int numberOfLikes;

    private int numberOfReTweets;

    private List<String> hashtags = new ArrayList<>();

    private List<String> mentions = new ArrayList<>();


}
