package com.groupC.twitter.service;

import com.groupC.twitter.model.Tweet;

import java.util.List;

public interface TweetService {

    public void addTweet(Tweet tweet);

    public Tweet getTweet(long tweetId);

    public List<Tweet> getTweets(long userId);


}
