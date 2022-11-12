package com.groupC.twitter.repository;

import com.groupC.twitter.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

}
