package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.exceptions.UserNotFoundException;
import com.groupC.twitter.model.Bookmark;
import com.groupC.twitter.model.Tweet;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.BookmarkRepository;
import com.groupC.twitter.repository.TweetRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.BookmarkService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public boolean getBookmark(long userId, long tweetId) {
        List<Bookmark> bookmarks  =bookmarkRepository.findByUserIdAndTweetId(userId,tweetId);
        if(bookmarks.size()>0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<BookmarkDto> getBookmarks(long userId){
        this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(HttpStatus.NOT_FOUND,"User doesn't exist"));
        List<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId);
        List<BookmarkDto> UserBookmarks = bookmarks.stream().map((bookmark) -> this.modelMapper.map(bookmark,BookmarkDto.class))
                .collect(Collectors.toList());
        return UserBookmarks;
    }
    @Override
    @Transactional
    public BookmarkDto addBookmark(BookmarkDto bookmarkDto){
        Bookmark bookmark = modelMapper.map(bookmarkDto,Bookmark.class);
        User user=this.userRepository.findById(bookmarkDto.getUserId()).orElseThrow(()-> new UserNotFoundException(HttpStatus.NOT_FOUND, "User doesn't exist"));
        Tweet tweet = this.tweetRepository.findById(bookmarkDto.getTweetId()).orElseThrow(()-> new NoSuchElementException("this Tweets ID does not exist"));
        bookmark.setUser(user);
        bookmark.setTweet(tweet);
        bookmarkRepository.save(bookmark);
        return modelMapper.map(bookmark,BookmarkDto.class);
    }

    @Override
    @Transactional
    public void removeBookmark(long userId, long tweetId) {
        if(getBookmark(userId,tweetId)){
            bookmarkRepository.deleteByUserIdAndTweetId(userId,tweetId);
        }
    }
}
