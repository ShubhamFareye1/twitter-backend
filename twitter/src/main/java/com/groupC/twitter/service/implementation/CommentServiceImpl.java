package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.CommentDto;
import com.groupC.twitter.model.Comment;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.repository.CommentRepository;
import com.groupC.twitter.repository.LikeRepository;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.CommentService;
import com.groupC.twitter.service.TweetService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;


    @Override
    public List<CommentDto> getTweetsCommets(long tweetId) {
        tweetService.getTweetById(tweetId);
        List<Comment> comments = commentRepository.findByTweetId(tweetId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new NoSuchElementException("This Comment ID does not exit"));
        Tweet tweet = tweetRepository.findById(comment.getTweetId()).get();
        tweet.decrementCommentCount();
        tweetRepository.save(tweet);
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {
        tweetService.getTweetById(commentDto.getTweetId());
        userService.getUser(commentDto.getUserId());
        Tweet tweet = tweetRepository.getReferenceById(commentDto.getTweetId());
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setUser(userRepository.getReferenceById(commentDto.getUserId()));
        tweet.incrementCommentCount();
        comment.setTweet(tweetRepository.save(tweet));
        Comment newComment = commentRepository.save(comment);
        CommentDto newCommentDto= modelMapper.map(newComment, CommentDto.class);
        return newCommentDto;
    }
}
