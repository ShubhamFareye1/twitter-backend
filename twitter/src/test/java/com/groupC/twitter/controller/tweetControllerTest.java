package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class tweetControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    void getSingleTweet() {
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange("http://localhost:8080/user/tweets/" + id, HttpMethod.GET, request, TweetDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
        }
    }
    @Test
    void getAllTweets(){
        int id = 1;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user/"+id+"/tweets", String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }catch (Exception e){

        }
    }

    @Test
    void getUserTweets(){
        try {
            ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user/tweets", String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }catch (Exception e){

        }
    }

    @Test
    void postSingleTweet(){
        TweetDto tweetDto= new TweetDto();
        tweetDto.setText("TestController of Tweets with user 1");
        tweetDto.setCreatedUserId(1);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(tweetDto,headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange("http://localhost:8080/user/tweets", HttpMethod.POST, request, TweetDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.CREATED);
        }catch (Exception e){
            assertEquals("404 : \"Wrong ID\"", e.getMessage());
        }
    }
    @Test
    void deleteTweetMap(){
        int id=2;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange("http://localhost:8080/user/tweets/"+id,HttpMethod.DELETE,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            assertEquals("400 : \"this Tweets ID does not exist\"",e.getMessage());
        }
    }


}