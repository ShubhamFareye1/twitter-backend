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

    private String image;

//    private Date tweetedDate;
    private Date createdDate;
    private int numberOFLikes;
    private int numberOFTweets;
    private int numberOfComments;

    private User postedUser;
    private User createdUser;
    private List<String> hashtags = new ArrayList<>();
    private List<String> mentions = new ArrayList<>();


}
