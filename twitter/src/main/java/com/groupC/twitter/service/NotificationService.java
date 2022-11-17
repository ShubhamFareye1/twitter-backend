package com.groupC.twitter.service;

import com.groupC.twitter.dto.NotificationDto;
import com.groupC.twitter.model.Notification;

import java.util.List;

public interface NotificationService {

    public List<NotificationDto> getNotification(long userId);

}
