package com.groupC.twitter.repository;

import com.groupC.twitter.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

}
