package com.groupC.twitter.repository;

import com.groupC.twitter.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    public List<Bookmark> findByUserId(Long userId);
}
