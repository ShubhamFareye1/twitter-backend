package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.exceptions.UserNameAlredyExistException;
import com.groupC.twitter.exceptions.UserNotFoundException;
import com.groupC.twitter.model.*;
import com.groupC.twitter.repository.*;
import com.groupC.twitter.service.HashtagService;
import com.groupC.twitter.service.TweetService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public TweetDto addTweet(TweetDto tweetdto, Long userId) {


        User user=this.userRepository.findById(tweetdto.getCreatedUserId()).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        tweet.setPostedUserId(userId);
        tweet.setCreatedUser(user);
        tweet.setPostedUser(user);
        List<Hashtagpost> hashtagposts = new ArrayList<>();
        Tweet newTweet = this.tweetRepository.save(tweet);
//        Optional.ofNullable(this.hashtagService.getHashtagsByNames(tweetdto.getHashtags()))
//                .ifPresent(
//                        hashtags -> {
//                            hashtags.forEach(
//                                    hashtag -> {
//                                        Hashtagpost hashtagpost = new Hashtagpost();
//                                        hashtagpost.setTweetId(newTweet.getTweetId());
//                                        hashtagpost.setTweetId(hashtag.getHashtagId());
//                                        hashtagposts.add(hashtagpost);
//                                    }
//                            );
//                        }
//                );
//        hashtagPostRepository.saveAll(hashtagposts);
        return this.modelMapper.map(newTweet,TweetDto.class);

    }


    @Override
    @Transactional
    public TweetDto addTweet(TweetDto tweetdto) {

        User user=this.userRepository.findById(tweetdto.getCreatedUserId()).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        tweet.setPostedUserId(tweetdto.getCreatedUserId());
        tweet.setCreatedUser(user);
        tweet.setPostedUser(user);
        List<Hashtagpost> hashtagposts = new ArrayList<>();
        Tweet newTweet = this.tweetRepository.save(tweet);
//        Optional.ofNullable(this.hashtagService.getHashtagsByNames(tweetdto.getHashtags()))
//                .ifPresent(
//                        hashtags -> {
//                            hashtags.forEach(
//                                    hashtag -> {
//                                        Hashtagpost hashtagpost = new Hashtagpost();
//                                        hashtagpost.setTweetId(newTweet.getTweetId());
//                                        hashtagpost.setTweetId(hashtag.getHashtagId());
//                                        hashtagposts.add(hashtagpost);
//                                    }
//                            );
//                        }
//                );
//        hashtagPostRepository.saveAll(hashtagposts);
        return this.modelMapper.map(newTweet,TweetDto.class);
    }

    @Override
    @Transactional
    public TweetDto reTweet(Long userId, Long tweetId) {

        User user=this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        TweetDto tweetdto = getTweetById(tweetId);
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
        tweet.setTweetId(0);
        tweet.setPostedUserId(userId);
        tweet.setPostedUser(user);
        tweet.setCreatedUser(userRepository.findById(tweetdto.getCreatedUserId()).get());
        tweet.incrementRepostCount();
        tweet.setCreatedDate(new Date());
        Tweet finalTweet = tweetRepository.save(tweet);
        return modelMapper.map(finalTweet,TweetDto.class);
    }

    @Override
    public TweetDto updateTweet(TweetDto tweetdto) {

        this.userRepository.findById(tweetdto.getCreatedUserId()).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        Tweet tweet = this.modelMapper.map(tweetdto,Tweet.class);
//        List<Hashtagpost> hashtagposts = new ArrayList<>();
        Tweet newTweet = this.tweetRepository.save(tweet);
        return this.modelMapper.map(newTweet,TweetDto.class);
    }

    @Override
    @Transactional
    public void deleteTweet(Long tweetId) {

        this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
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

        Pageable p = PageRequest.of(0,80, Sort.by("tweetId").descending());
        Page<Tweet> pagetweets = this.tweetRepository.findAll(p);
        List<Tweet> tweets = pagetweets.getContent();

        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    public List<TweetDto> getTweetsByUser(long userId) {

        this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        userService.getUser(userId);
        List<Tweet>tweets = this.tweetRepository.findByPostedUserIdOrderByCreatedDateDesc(userId);

        List<TweetDto> tweetDtos = tweets.stream().map((tweet)->this.modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    @Override
    public List<TweetDto> getFeeds(long userId) {
        UserDto userDto = userService.getUser(userId);

        List<TweetDto> tweets = new ArrayList<>();
        Optional.ofNullable(userService.getFollowings(userId)).ifPresent(
                followers->{
                    followers.forEach(
                            user->{
                                tweets.addAll(getTweetsByUser(user.getUserId()));
                            }
                    );
                }
        );
        tweets.addAll(getTweetsByUser(userId));

        Collections.sort(tweets, new Comparator<TweetDto>() {
            @Override public int compare(final TweetDto o1, final TweetDto o2) {
                if (o1.getTweetId() > o2.getTweetId()) {
                    return -1;
                } else if (o1.getTweetId() < o2.getTweetId()) {
                    return 1;
                }
                return 0;
            }
        });



        return tweets;
    }

    @Override
    public List<TweetDto> searchTweets(String keyword) {
        List<Tweet> tweets = this.tweetRepository.searchByText("%"+keyword+"%");
        List<TweetDto> tweetDtos = tweets.stream().map(tweet -> modelMapper.map(tweet,TweetDto.class))
                .collect(Collectors.toList());
        return tweetDtos;
    }

    public boolean getLike(long tweetId, long userId){
        List<Like> like = likeRepository.findByUserIdAndTweetId(userId,tweetId);
        if(like.size()>0)
            return true;
        else
            return false;
    }

    @Override
    @Transactional
    public int addLike(long tweetId, long userId) {
        Tweet tweet = this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));

//        Like like = this.likeRepository.findByUserId(userId);
//        if(like.getLikeId()>0){
//            throw new UserNameAlredyExistException( HttpStatus.BAD_REQUEST,"You're already like this post before");
//        }
        tweet.incrementLikeCount();
        User user = modelMapper.map(userService.getUser(userId),User.class);
        Like likeMapping = new Like();
        likeMapping.setTweetId(tweetId);
        likeMapping.setUserId(userId);
        likeMapping.setUser(user);
        likeMapping.setTweet(tweet);
        likeRepository.save(likeMapping);
        tweetRepository.save(tweet);
        Notification notification = new Notification();
        notification.setMsg(user.getUserName() +" like your tweet");
        notification.setUserId(tweet.getCreatedUserId());
        notification.setUser(tweet.getCreatedUser());
        notification.setTweetId(tweet.getTweetId());
        notificationRepository.save(notification);
        return tweet.getNumberOfLikes();
    }

    @Override
    @Transactional
    public int removeLike(long tweetId, long userId) {

        Tweet tweet = this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
//        Like like = this.likeRepository.findByUserId(userId);
//        this.likeRepository.findById(like.getLikeId()).orElseThrow(()->new NoSuchElementException("you're not like this post before"));
        tweet.decrementLikeCount();
        User user = modelMapper.map(userService.getUser(userId),User.class);
        likeRepository.deleteByUserIdAndTweetId(userId,tweetId);
        tweetRepository.save(tweet);
        return tweet.getNumberOfLikes();
    }

}
