package com.groupC.twitter.controller;

import com.groupC.twitter.dto.CommentDto;
import com.groupC.twitter.dto.TweetDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CommentControllerTest {

    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8080/user";

    @Test
    void getCommentOfTweets(){
        int tweetId = 2100;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/tweets/"+tweetId+"/comments", String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
        }
    }
    @Test
    void deleteSingleComment(){
        int commentId=15;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange(baseUrl+"/tweets/comments/"+commentId,HttpMethod.DELETE,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.OK);
        }
        catch (Exception e){
            assertEquals("400 : \"This Comment ID does not exit\"",e.getMessage());
        }
    }

    @Test
    void addComment(){
        CommentDto commentDto= new CommentDto();
        commentDto.setCommentText("TestController of Comment with user 1");
        long userId = 1;
        long tweetId =7;
        commentDto.setUserId(userId);
        commentDto.setTweetId(tweetId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<CommentDto> request = new HttpEntity<CommentDto>(commentDto,headers);
        try {
            ResponseEntity<CommentDto> res = restTemplate.exchange(baseUrl+"/tweets/comments", HttpMethod.POST, request, CommentDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.CREATED);
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

}