package com.groupC.twitter.repository;

import com.groupC.twitter.model.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {

    public Hashtag findByName(String name);

    public List<Hashtag> findByNameIn(List<String> names);
}
