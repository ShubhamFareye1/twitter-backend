package com.groupC.twitter.controller;

import com.groupC.twitter.dto.CommentDto;
import com.groupC.twitter.service.CommentService;
import com.groupC.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/tweets")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{tweetId}/comments")
    public ResponseEntity getComments(@PathVariable("tweetId") Long id){
        return new ResponseEntity(commentService.getTweetsCommets(id), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("commentId") Long id){
        commentService.deleteComment(id);
        return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity addComment(@RequestBody CommentDto commentDto){
        return new ResponseEntity(commentService.addComment(commentDto),HttpStatus.CREATED);
    }


}
