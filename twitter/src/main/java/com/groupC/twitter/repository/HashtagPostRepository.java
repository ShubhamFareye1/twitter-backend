package com.groupC.twitter.repository;

import com.groupC.twitter.model.Hashtag;
import com.groupC.twitter.model.Hashtagpost;
import com.groupC.twitter.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HashtagPostRepository extends JpaRepository<Hashtagpost,Long> {

    public List<Tweet> findByHashtagId(long hashtagId);

    public List<Hashtag> findByTweetId(long tweetId);
}
