package com.groupC.twitter.repository;

import com.groupC.twitter.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
}
