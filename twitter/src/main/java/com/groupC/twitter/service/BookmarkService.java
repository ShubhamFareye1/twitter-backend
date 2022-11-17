package com.groupC.twitter.service;

import com.groupC.twitter.dto.BookmarkDto;

import java.util.List;

public interface BookmarkService {

    public boolean getBookmark(long userId,long tweetId);

    public List<BookmarkDto> getBookmarks(long userId);

    public BookmarkDto addBookmark(BookmarkDto bookmarkDto);

    public void removeBookmark(long userId, long tweetId);
}
