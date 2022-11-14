package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.CommentDto;
import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.Comment;
import com.groupC.twitter.model.Notification;
import com.groupC.twitter.repository.*;
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

    @Autowired
    private NotificationRepository notificationRepository;


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
        commentRepository.findById(commentId).orElseThrow(()->new NoSuchElementException("This Comment ID does not exit"));
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public CommentDto addComment(CommentDto commentDto) {
        TweetDto tweetDto=tweetService.getTweetById(commentDto.getTweetId());
        UserDto userDto = userService.getUser(commentDto.getUserId());
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setUser(userRepository.getReferenceById(commentDto.getUserId()));
        comment.setTweet(tweetRepository.getReferenceById(commentDto.getTweetId()));
        Comment newComment = commentRepository.save(comment);
        CommentDto newCommentDto= modelMapper.map(newComment, CommentDto.class);
        Notification notification = new Notification();
        notification.setMsg(userDto.getUserName() +" comment on your tweet");
        notification.setUserId(tweetDto.getCreatedUserId());
        //notification.setUser(tweetDto.getCreatedUser());
        notificationRepository.save(notification);
        return newCommentDto;
    }
}
