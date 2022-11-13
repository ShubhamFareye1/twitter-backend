package com.groupC.twitter.repository;

import com.groupC.twitter.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    List<Comment> findByUserId(Long userId);
    public List<Comment> findByTweetId(Long tweetId);
    public  void deleteByUserIdAndTweetId(Long userId, Long tweetId);
    public Comment findByUserIdAndTweetId(Long userId, Long tweetId);
}
