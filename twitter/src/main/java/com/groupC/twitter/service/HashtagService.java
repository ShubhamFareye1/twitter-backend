package com.groupC.twitter.service;

import com.groupC.twitter.dto.HashtagDto;
import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.model.Hashtag;

import java.util.List;

public interface HashtagService {

    public List<HashtagDto> getHashtags();

    public List<TweetDto> getPosts(String name);

    public List<Hashtag> getHashtagsByNames(List<String> hashes);
}
