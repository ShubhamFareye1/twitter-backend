package com.groupC.twitter.repository;

import com.groupC.twitter.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {

    public void deleteByUserIdAndTweetId(Long userId, Long tweetId);

    @Query("select DISTINCT p.tweetId from Like p where p.userId=:key")
    public List<Long> findTweetByUserId(@Param("key") Long userId);

    public List<Like> findByUserIdAndTweetId(Long userId, Long tweetId);
}
