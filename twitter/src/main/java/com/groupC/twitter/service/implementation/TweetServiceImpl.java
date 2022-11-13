package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.TweetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TweetDto addTweet(TweetDto tweetdto, Long userId) {

        this.userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User ID is not found"));
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        tweet.setCreatedUserId(userId);
        Tweet newTweet = this.tweetRepository.save(tweet);
        return this.modelMapper.map(newTweet,TweetDto.class);

    }


    @Override
    public TweetDto addTweet(TweetDto tweetdto) {
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        Tweet newTweet = this.tweetRepository.save(tweet);
        return this.modelMapper.map(newTweet,TweetDto.class);
    }

    @Override
    public void updateTweet(TweetDto tweet, Long tweetId) {

    }


    @Override
    public void deleteTweet(Long tweetId) {

        this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        this.tweetRepository.deleteById(tweetId);
    }

    @Override
    public TweetDto getTweetById(Long tweetId) {
        this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        Tweet tweet = this.tweetRepository.findById(tweetId).get();
        return this.modelMapper.map(tweet,TweetDto.class);
    }

    @Override
    public List<TweetDto> getTweets() {
        List<Tweet> tweets = this.tweetRepository.findAll();

        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    public List<TweetDto> getTweetsByUser(long userId) {

        this.userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User ID is not found"));
        List<Tweet>tweets = this.tweetRepository.findByCreatedUserId(userId);

        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    @Transactional
    public long addLike(long tweetId, long userId) {
        return 0;
    }

    @Override
    @Transactional
    public long removeLike(long tweetId, long userId) {
        return 0;
    }
//    public List<Tweet> getTweets(long userId){
//        return
//    }

}
