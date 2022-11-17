package com.groupC.twitter.service.implementation;

import com.groupC.twitter.exceptions.UserNotFoundException;
import com.groupC.twitter.model.Like;
import com.groupC.twitter.model.Notification;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.LikeRepository;
import com.groupC.twitter.repository.NotificationRepository;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.LikeService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.AlreadyBuiltException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public boolean getLike(long tweetId, long userId){
        List<Like> like = likeRepository.findByUserIdAndTweetId(userId,tweetId);
        if(like.size()>0)
            return true;
        else
            return false;
    }

    @Override
    public List<Long> getTweetLike(long userId){
        return likeRepository.findTweetByUserId(userId);
    }

    @Override
    @Transactional
    public int addLike(long tweetId, long userId) {
        Tweet tweet = this.tweetRepository.findById(tweetId).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        this.userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        if(getLike(userId,tweetId)){
            throw new AlreadyBuiltException("Already Liked");
        }

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
        notification.setActionUser(user);
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
