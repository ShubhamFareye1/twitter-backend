package com.groupC.twitter.repository;

import com.groupC.twitter.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    List<Comment> findByUserId(Long userId);

    @Query("select p from Comment p where p.tweetId=:key order by commentedAt desc")
    public List<Comment> findByTweetId(@Param("key") Long tweetId);
    public  void deleteByUserIdAndTweetId(Long userId, Long tweetId);
    public Comment findByUserIdAndTweetId(Long userId, Long tweetId);
}
