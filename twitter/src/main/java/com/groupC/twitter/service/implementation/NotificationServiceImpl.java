package com.groupC.twitter.service.implementation;

import com.groupC.twitter.dto.NotificationDto;
import com.groupC.twitter.model.Notification;
import com.groupC.twitter.repository.NotificationRepository;
import com.groupC.twitter.service.NotificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<NotificationDto> getNotification(long userId) {
        List<Notification> notificationList =  notificationRepository.findByUserIdOrderByNotificationIdDesc(userId);
        List<NotificationDto>notificationDtos=notificationList.stream().map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
        return notificationDtos;
    }
}
