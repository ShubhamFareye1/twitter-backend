package com.groupC.twitter.service;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.model.Tweet;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TweetService {


 public TweetDto addTweet(TweetDto tweetdto, Long userId);

 public TweetDto addTweet(TweetDto tweetdto);

    public TweetDto reTweet(Long userId, Long tweetId);

    public TweetDto updateTweet(TweetDto tweet);

    public void deleteTweet(Long tweetId);

    public TweetDto getTweetById(Long tweetId);

    public List<TweetDto>getTweets();

    public List<TweetDto> getTweetsByUser(long userId);

    public List<TweetDto> getFeeds(long userId);

    public  List<TweetDto> searchTweets(String keyword);




}
