package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.User;
import com.groupC.twitter.repository.NotificationRepository;
import com.groupC.twitter.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<String> getNotification(long userId) {
        List<String> notificationList =  notificationRepository.findMsgByUserId(userId);
        return notificationList;
    }
}
