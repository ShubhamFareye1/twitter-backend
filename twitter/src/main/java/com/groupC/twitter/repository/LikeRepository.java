package com.groupC.twitter.repository;

import com.groupC.twitter.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    public void deleteByUserIdAndTweetId(Long userId, Long tweetId);
    public Like findByUserId(Long userId);

    public List<Like> findByUserIdAndTweetId(Long userId, Long tweetId);
}
