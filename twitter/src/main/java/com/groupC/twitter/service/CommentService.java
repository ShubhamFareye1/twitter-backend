package com.groupC.twitter.service;

import com.groupC.twitter.dto.CommentDto;

import java.util.List;

public interface CommentService {

    public List<CommentDto> getTweetsCommets(long tweetId);

    public void deleteComment(Long commentId);

    public CommentDto addComment(CommentDto commentDto);
}
