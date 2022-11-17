package com.groupC.twitter.service;

import com.groupC.twitter.dto.MessagesDto;
import com.groupC.twitter.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface MessageService {

    public List<MessagesDto> addMessage(MessagesDto messagesDto);

    public List<MessagesDto> getMessage(long senderId,long recieverId);

    public List<UserDto> getMessageUser(long userId);

}
