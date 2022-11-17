package com.groupC.twitter.dto;

import com.groupC.twitter.model.User;
import lombok.Data;

import java.util.Date;

@Data
public class MessagesDto {
   private long messageId;
   private String text;
   private Date messageDate;
   private long senderId;
   private long recieverId;
   private User sender;
   private User reciever;
}
