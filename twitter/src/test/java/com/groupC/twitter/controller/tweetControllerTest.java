package com.groupC.twitter.controller;

import com.groupC.twitter.dto.TweetDto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class tweetControllerTest {

    RestTemplate restTemplate = new RestTemplate();


    void getSingleTweet() {
        int id = 5;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<TweetDto> request = new HttpEntity<TweetDto>(headers);
        try {
            ResponseEntity<TweetDto> res = restTemplate.exchange("http://localhost:8082/todos/" + id, HttpMethod.GET, request, Todo.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("404 : \"Wrong ID\"", e.getMessage());
        }
    }
}