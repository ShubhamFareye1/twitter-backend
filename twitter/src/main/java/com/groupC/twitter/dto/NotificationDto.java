package com.groupC.twitter.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private long notificationId;
    private String msg;
    private long userId;
    private long tweetId;
}
