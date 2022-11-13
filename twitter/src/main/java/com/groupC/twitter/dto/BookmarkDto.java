package com.groupC.twitter.dto;


import lombok.Data;

@Data
public class BookmarkDto {
    private long bookmarkId;
    private long userId;
    private long tweetId;
}
