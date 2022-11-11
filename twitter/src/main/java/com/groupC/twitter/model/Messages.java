package com.groupC.twitter.model;

import lombok.Data;

import java.util.Date;

@Data
public class Messages {
//   private long messageId;
   private String text;
   private Date messageDate;
   private long sender;
   private long reciever;
}
