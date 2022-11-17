package com.groupC.twitter.repository;

import com.groupC.twitter.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    //@Query("select msg from Notification where user_id = :key")
    public List<Notification> findByUserIdOrderByNotificationIdDesc(Long userId);

}
