package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.Hashtagpost;
import com.groupC.twitter.model.Like;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.*;
import com.groupC.twitter.service.HashtagService;
import com.groupC.twitter.service.TweetService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HashtagPostRepository hashtagPostRepository;

    @Autowired
    private HashtagService hashtagService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeRepository likeRepository;

    @Override
    @Transactional
    public TweetDto addTweet(TweetDto tweetdto, Long userId) {

//        this.userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User ID is not found"));
        userService.getUser(userId);
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        tweet.setCreatedUserId(userId);
        List<Hashtagpost> hashtagposts = new ArrayList<>();
        Tweet newTweet = this.tweetRepository.save(tweet);
        Optional.ofNullable(this.hashtagService.getHashtagsByNames(tweetdto.getHashtags()))
                .ifPresent(
                        hashtags -> {
                            hashtags.forEach(
                                    hashtag -> {
                                        Hashtagpost hashtagpost = new Hashtagpost();
                                        hashtagpost.setTweetId(newTweet.getTweetId());
                                        hashtagpost.setTweetId(hashtag.getHashtagId());
                                        hashtagposts.add(hashtagpost);
                                    }
                            );
                        }
                );
        hashtagPostRepository.saveAll(hashtagposts);
        return this.modelMapper.map(newTweet,TweetDto.class);

    }

    @Override
    @Transactional
    public TweetDto addTweet(TweetDto tweetdto) {

//        this.userRepository.findById(tweetdto.getCreatedUserId()).orElseThrow(()-> new NoSuchElementException("User ID is not found"));
        userService.getUser(tweetdto.getCreatedUserId());
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);

        List<Hashtagpost> hashtagposts = new ArrayList<>();
        Tweet newTweet = this.tweetRepository.save(tweet);
        Optional.ofNullable(this.hashtagService.getHashtagsByNames(tweetdto.getHashtags()))
                .ifPresent(
                        hashtags -> {
                            hashtags.forEach(
                                    hashtag -> {
                                        Hashtagpost hashtagpost = new Hashtagpost();
                                        hashtagpost.setTweetId(newTweet.getTweetId());
                                        hashtagpost.setTweetId(hashtag.getHashtagId());
                                        hashtagposts.add(hashtagpost);
                                    }
                            );
                        }
                );
        hashtagPostRepository.saveAll(hashtagposts);
        return this.modelMapper.map(newTweet,TweetDto.class);
    }

    @Override
    public void updateTweet(TweetDto tweet, Long tweetId) {

    }

    @Override
    @Transactional
    public void deleteTweet(Long tweetId) {

//        this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        getTweetById(tweetId);
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

//        this.userRepository.findById(userId).orElseThrow(()-> new NoSuchElementException("User ID is not found"));
        userService.getUser(userId);
        List<Tweet>tweets = this.tweetRepository.findByCreatedUserId(userId);

        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    public List<TweetDto> getFeeds(long userId) {
        UserDto userDto = userService.getUser(userId);

        List<TweetDto> tweets = new ArrayList<>();
        Optional.ofNullable(userService.getFollowers(userId)).ifPresent(
                followers->{
                    followers.forEach(
                            user->{
                                tweets.addAll(getTweetsByUser(user.getUserId()));
                            }
                    );
                }
        );
        return tweets;
    }

    @Override
    @Transactional
    public int addLike(long tweetId, long userId) {
        Tweet tweet = this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        tweet.incrementLikeCount();
        User user = modelMapper.map(userService.getUser(userId),User.class);

        Like likeMapping = new Like();
        likeMapping.setTweetId(tweetId);
        likeMapping.setUserId(userId);
        likeRepository.save(likeMapping);
        tweetRepository.save(tweet);

        return tweet.getNumberOfLikes();
    }

    @Override
    @Transactional
    public int removeLike(long tweetId, long userId) {
        Tweet tweet = this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        tweet.decrementLikeCount();
        User user = modelMapper.map(userService.getUser(userId),User.class);

        likeRepository.deleteByUserIdAndTweetId(userId,tweetId);
        tweetRepository.save(tweet);
        return 0;
    }

}
