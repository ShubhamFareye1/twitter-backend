package com.groupC.twitter.model;

import lombok.Data;

import javax.persistence.*;

@Data @Entity @Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long notificationId;
    private String msg;
    private long userId;
    private long tweetId;

    @ManyToOne
    @JoinColumn(updatable = false,nullable = false)
    private User user;

    @ManyToOne
    private User actionUser;
}
