package com.groupC.twitter.repository;

import com.groupC.twitter.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

    List<Tweet> findByCreatedUserId(Long createdUserId);
}
