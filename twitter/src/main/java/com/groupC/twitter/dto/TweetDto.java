package com.groupC.twitter.dto;

import com.groupC.twitter.model.User;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.*;

@Data
public class TweetDto {

    private long tweetId;

    private long postedUserId;

    private long createdUserId;

    private String text;

//    private Date tweetedDate;
    private Date createdDate;
    private int numberOFLikes;
    private int numberOFTweets;
    private List<String> hashtags = new ArrayList<>();
    private List<String> mentions = new ArrayList<>();
    private List<String> imagess = new ArrayList<>(4);


}
