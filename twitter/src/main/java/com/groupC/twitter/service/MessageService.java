package com.groupC.twitter.service;

import com.groupC.twitter.dto.MessagesDto;

import java.util.List;

public interface MessageService {

    public List<MessagesDto> addMessage(MessagesDto messagesDto);

    public List<MessagesDto> getMessage(long senderId,long recieverId);

}
