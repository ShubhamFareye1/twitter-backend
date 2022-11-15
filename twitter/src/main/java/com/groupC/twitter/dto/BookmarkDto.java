package com.groupC.twitter.dto;

import com.groupC.twitter.model.Tweet;
import lombok.Data;

@Data
public class BookmarkDto {
    private long bookmarkId;
    private long userId;
    private long tweetId;
    private Tweet tweet;
}
