package com.groupC.twitter.repository;

import com.groupC.twitter.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    public List<String> getNotificationMsg(long userId);

}
