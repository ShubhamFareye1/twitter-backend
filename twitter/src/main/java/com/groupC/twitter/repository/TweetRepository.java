package com.groupC.twitter.repository;

import com.groupC.twitter.model.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet,Long> {

   public List<Tweet> findByCreatedUserIdOrderByCreatedDateDesc(Long createdUserId);

   @Query("select p from Tweet p where p.text like :key")
   public List<Tweet> searchByText(@Param("key") String text);

   public List<Tweet> findByPostedUserIdOrderByCreatedDateDesc(Long postedUserId);
   
}
