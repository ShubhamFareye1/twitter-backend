package com.groupC.twitter.dto;

import com.groupC.twitter.model.User;
import lombok.Data;

@Data
public class NotificationDto {
    private long notificationId;
    private String msg;
    private long userId;
    private long tweetId;
    private User actionUser;
    private User user;
}
