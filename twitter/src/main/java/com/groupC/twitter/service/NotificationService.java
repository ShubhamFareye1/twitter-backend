package com.groupC.twitter.service;

import com.groupC.twitter.model.Notification;

import java.util.List;

public interface NotificationService {

    public List<Notification> getNotification(long userId);

}
