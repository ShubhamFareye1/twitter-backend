package com.groupC.twitter.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private long userId;

    private String userName;

    private String name;

    private String password;

    private Date dob;

    private  Date createdAt;

    private Boolean isVerified;

    private String roles;

    private String avatar;

    private int numberOfFollower;

    private int numberOfFollowing;

}
