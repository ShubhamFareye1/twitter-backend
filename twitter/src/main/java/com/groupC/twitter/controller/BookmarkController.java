package com.groupC.twitter.controller;

import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.service.BookmarkService;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    //this api is use for add following of user.
    @GetMapping("/{userId}/bookmark/{tweetId}")
    public ResponseEntity getBookmark(@PathVariable("userId") long userId, @PathVariable("tweetId") long tweetId){
        return new ResponseEntity(bookmarkService.getBookmark(userId,tweetId), HttpStatus.OK);
    }

    //this api is use for add bookmark of user
    @PostMapping("/bookmark")
    public ResponseEntity setBookmark(@RequestBody BookmarkDto bookmark){
        return new ResponseEntity(bookmarkService.addBookmark(bookmark),HttpStatus.OK);
    }
    // this api is use for fetch all bookmark of user.
    @GetMapping("/bookmark/{userId}")
    public ResponseEntity getBookmark(@PathVariable("userId") long userId){
        return new ResponseEntity(bookmarkService.getBookmarks(userId),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/bookmark/{tweetId}")
    public ResponseEntity removeBookmark(@PathVariable("userId") long userId,@PathVariable("tweetId") long tweetId){
        bookmarkService.removeBookmark(userId,tweetId);
        return new ResponseEntity("successfully removed",HttpStatus.OK);
    }
}
