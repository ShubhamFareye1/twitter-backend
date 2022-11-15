package com.groupC.twitter.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessagesDto {
   private long messageId;
   private String text;
   private Date messageDate;
   private long senderId;
   private long recieverId;
}
