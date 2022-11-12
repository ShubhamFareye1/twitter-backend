package com.groupC.twitter.service;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.model.Tweet;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TweetService {


 TweetDto addTweet(TweetDto tweetdto, Long userId);

 public TweetDto addTweet(TweetDto tweetdto);

    public void updateTweet(TweetDto tweet, Long tweetId);

    public void deleteTweet(Long tweetId);

    public TweetDto getTweetById(Long tweetId);

    public List<TweetDto>getTweets();

    public List<TweetDto> getTweetsByUser(long userId);

    public long addLike(long tweetId, long userId);

    public  long removeLike(long tweetId, long userId);


}