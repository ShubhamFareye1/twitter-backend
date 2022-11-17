package com.groupC.twitter.controller;

import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.dto.TweetDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    RestTemplate restTemplate = new RestTemplate();

    String baseUrl = "http://localhost:8080";

    @Test
    void signup(){
        UserDto userdto= new UserDto();
        String username = "sajaltest2";
        userdto.setUserName(username);
        String name = "Sajal";
        userdto.setName(name);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> request = new HttpEntity<UserDto>(userdto,headers);
        try {
            ResponseEntity<UserDto> res = restTemplate.exchange(baseUrl+"/user", HttpMethod.POST, request, UserDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        }catch (RuntimeException e){
            assertEquals("400 : \"User already exist\"",e.getMessage());
        }

    }

    @Test
    void updateUser(){
        UserDto userDto= new UserDto();
        long id = 1;
        userDto.setUserId(id);
        userDto.setPassword("Test Password");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> request = new HttpEntity<UserDto>(userDto,headers);
        try {
            ResponseEntity<UserDto> res = restTemplate.exchange(baseUrl+"/user", HttpMethod.PUT, request, UserDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        }
        catch (Exception e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void getSingleUser(){
        int id = 12;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> request = new HttpEntity<UserDto>(headers);
        try {
            ResponseEntity<UserDto> res = restTemplate.exchange(baseUrl+"/user/"+ id, HttpMethod.GET, request, UserDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void getAllUser(){
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"/user", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getUserbyUsername(){
        String username="ganesh12";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> request = new HttpEntity<UserDto>(headers);
        try {
            ResponseEntity<UserDto> res = restTemplate.exchange(baseUrl+"/user/username/"+ username, HttpMethod.GET, request, UserDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void deleteUser(){
        int id=53;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange(baseUrl+"/user/"+id,HttpMethod.DELETE,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.OK);
        }
        catch (Exception e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void addfollower(){
        int userId = 8;
        int followeId = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/follow/"+userId+"/"+followeId,
                    HttpMethod.PUT,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void removefollower(){
        int userId = 8;
        int followeId = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/follow/"+userId+"/"+followeId,
                    HttpMethod.DELETE,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
     void removefollowing(){
        int userId = 8;
        int followeId = 25;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/following/"+userId+"/"+followeId,
                    HttpMethod.DELETE,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void  getfollower(){
        int id = 1;
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(baseUrl+"/user/"+id+"/followers", String.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void getfollowing(){
        int id = 8;
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(baseUrl+"/user/"+id+"/followings", String.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void addfollowing(){
        int userId = 8;
        int followingId = 25;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(baseUrl+"/user/following/"+userId+"/"+followingId,
                    HttpMethod.POST,request,String.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
        }
        catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void addBookmark(){
        BookmarkDto bookmarkDto= new BookmarkDto();
        long userId=8;
        long tweetId = 25;
        bookmarkDto.setUserId(userId);
        bookmarkDto.setTweetId(tweetId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<BookmarkDto> request = new HttpEntity<BookmarkDto>(bookmarkDto,headers);
        try {
            ResponseEntity<BookmarkDto> res = restTemplate.exchange(baseUrl+"/user/bookmark", HttpMethod.POST, request, BookmarkDto.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        }catch (RuntimeException e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void getUserBookmark(){
        int id = 8;
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(baseUrl+"/user/bookmark/"+id, String.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void requestbluetick(){
        int id=8;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange(baseUrl+"/user/bluetick/"+id,HttpMethod.PUT,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.OK);
        }
        catch (Exception e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void getRequestedBluetick(){
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(baseUrl+"/user/admin/bluetick", String.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

    @Test
    void addBluetickStatus(){
        int id=3;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<String>(headers);
        try {
            ResponseEntity res = restTemplate.exchange(baseUrl+"/user/admin/bluetick/status/"+id,HttpMethod.PUT,request,String.class);
            assertEquals(res.getStatusCode(),HttpStatus.OK);
        }
        catch (Exception e){
            assertEquals("400 : \"User doesn't exist\"",e.getMessage());
        }
    }

    @Test
    void getNotification(){
        int id = 1;
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(baseUrl+"/user/notification/"+id, String.class);
            assertEquals(res.getStatusCode(), HttpStatus.OK);
        } catch (Exception e) {
            assertEquals("400 : \"User doesn't exist\"", e.getMessage());
        }
    }

}
