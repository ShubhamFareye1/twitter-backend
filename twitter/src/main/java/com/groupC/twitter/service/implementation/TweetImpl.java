package com.groupC.twitter.service.implementation;

import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetImpl extends UserService {
    @Autowired
    TweetRepository tweetRepository;

    public void addTweet(Tweet tweet){
        tweetRepository.save(tweet);
    }
//    public List<Tweet> getTweets(long userId){
//        return
//    }

}
