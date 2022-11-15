package com.groupC.twitter.service.implementation;

import com.groupC.twitter.model.Notification;
import com.groupC.twitter.repository.NotificationRepository;
import com.groupC.twitter.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notification> getNotification(long userId) {
        List<Notification> notificationList =  notificationRepository.findByUserId(userId);
        return notificationList;
    }
}
