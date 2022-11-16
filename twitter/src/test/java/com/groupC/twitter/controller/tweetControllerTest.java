package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class tweetControllerTest {

    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8080";


    @Test
    void login(){

    }

    @Test
    void getSingleTweet() {
        int id = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange(baseUrl+"/user/tweets/" + id, HttpMethod.GET, request, TweetDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
        }
    }
    @Test
    void getAllTweets(){
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user/tweets", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getUserTweets(){
        int id = 1;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user/"+id+"/tweets", String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void getUserFeed(){
        int id = 89;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user/"+id+"/feeds", String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void postSingleTweet(){
        TweetDto tweetDto= new TweetDto();
        tweetDto.setText("TestController of Tweets with user 1");
        int userId = 1;
        tweetDto.setCreatedUserId(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(tweetDto,headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange(baseUrl+"/user/tweets", HttpMethod.POST, request, TweetDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.CREATED);
//            assertEquals(res.getBody().getText(),"TestController of Tweets with user 1");
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void postTweet(){
        TweetDto tweetDto= new TweetDto();
        tweetDto.setText("TestController of Tweets with user 1");
        int userId = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(tweetDto,headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange(baseUrl+"/user/"+userId+"/tweets", HttpMethod.POST, request, TweetDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.CREATED);
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void postReTweet(){
        int userId = 1;
        int tweetId = 5;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/"+userId+"/retweets/"+tweetId,
                    HttpMethod.POST,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
//        catch (NoSuchElementException e) {
//            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
//        }
        catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void deleteTweetMap(){
        int id=54;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange(baseUrl+"/user/tweets/"+id,HttpMethod.DELETE,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            assertEquals("400 : \"this Tweets ID does not exist\"",e.getMessage());
        }
    }

    @Test
    void searchTweet(){
        String keyword = "this";
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user/tweets/search/"+keyword, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getLike(){
        int userId = 8;
        int tweetId = 25;
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user/"+userId+"/tweets/"+tweetId, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void addLike(){
        int userId = 8;
        int tweetId = 25;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/"+userId+"/tweets/"+tweetId,
                    HttpMethod.POST,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
        catch (RuntimeException e){
            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
        }
    }

    @Test
    void removeLike(){
        int userId = 8;
        int tweetId = 25;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/"+userId+"/tweets/"+tweetId,
                    HttpMethod.DELETE,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
        catch (RuntimeException e){
            assertEquals("400 : \"this Tweets ID does not exist\"", e.getMessage());
        }
    }

}