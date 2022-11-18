package com.groupC.twitter.controller;

import com.groupC.twitter.dto.MessagesDto;
import com.groupC.twitter.service.MessageService;
import com.groupC.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;

    //this api is use for post the message of user to other user of his list.
    @PostMapping("/message")
    public ResponseEntity userMessage(@RequestBody MessagesDto messagesDto){
        return new ResponseEntity(messageService.addMessage(messagesDto), HttpStatus.OK);
    }

    //this api is use for getting all messages of users to other users.
    @GetMapping("/message/{senderId}/{recieverId}")
    public ResponseEntity getUserMessage(@PathVariable("senderId") long senderId , @PathVariable("recieverId") long recieverId){
        return new ResponseEntity(messageService.getMessage(senderId,recieverId),HttpStatus.OK);
    }

    @GetMapping("/message/{userId}")
    public ResponseEntity getUsers(@PathVariable("userId") long userId){
        return new ResponseEntity(messageService.getMessageUser(userId),HttpStatus.OK);
    }
}
