package com.groupC.twitter.service;

import java.util.List;

public interface LikeService {

    public boolean getLike(long tweetId, long userId);

    public List<Long> getTweetLike(long userId);

    public int addLike(long tweetId, long userId);

    public  int removeLike(long tweetId, long userId);
}
