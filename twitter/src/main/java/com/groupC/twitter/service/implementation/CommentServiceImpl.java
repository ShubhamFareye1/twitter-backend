package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.CommentDto;
import com.groupC.twitter.model.Comment;
import com.groupC.twitter.repository.CommentRepository;
import com.groupC.twitter.repository.LikeRepository;
import com.groupC.twitter.service.CommentService;
import com.groupC.twitter.service.TweetService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<CommentDto> getTweetsCommets(long tweetId) {
        tweetService.getTweetById(tweetId);
        List<Comment> comments = commentRepository.findByTweetId(tweetId);
        List<CommentDto> commentDtos = comments.stream().map(comment -> this.modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(()->new NoSuchElementException("This Comment ID does not exit"));
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto addComment(CommentDto commentDto) {
        tweetService.getTweetById(commentDto.getTweetId());
        userService.getUser(commentDto.getUserId());
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }
}
