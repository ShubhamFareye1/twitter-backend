package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.MessagesDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.Messages;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.MessageRepository;
import com.groupC.twitter.repository.UserRepository;
import com.groupC.twitter.service.MessageService;
import com.groupC.twitter.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    private UserService userService;
    @Override
    public List<MessagesDto> addMessage(MessagesDto messagesDto) {
        Messages messages = modelMapper.map( messagesDto,Messages.class);
        User sender = userRepository.findById(messages.getSenderId()).get();
        User reciever = userRepository.findById(messages.getRecieverId()).get();
        messages.setSender(sender);
        messages.setReciever(reciever);
        messageRepository.save(messages);
        List<Messages> messageList = messageRepository.findBySenderIdRecieverId(messagesDto.getSenderId(),messagesDto.getRecieverId());
        List<MessagesDto> request = messageList.stream().map((message)->this.modelMapper.map(message,MessagesDto.class))
                .collect(Collectors.toList());
        return request;
    }

    @Override
    public List<MessagesDto> getMessage(long senderId, long recieverId) {
        List<Messages> messageList = messageRepository.findBySenderIdRecieverId(senderId,recieverId);
        List<MessagesDto> request1 = messageList.stream().map((message)->this.modelMapper.map(message,MessagesDto.class))
                .collect(Collectors.toList());
        return request1;
    }

    @Override
    public List<UserDto> getMessageUser(long userId) {
        Set<Long> myUserId = messageRepository.getSenderUsers(userId);
        myUserId.addAll(messageRepository.getRecieverUsers(userId));
        List<UserDto>userDtos = myUserId.stream().map((myuser)->userService.getUser(myuser)).collect(Collectors.toList());
        return userDtos;
    }
}
