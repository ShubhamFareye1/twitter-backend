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

    private Integer isVerified;

    private boolean roles;

    private String avatar;

    private String bannerImage;

    private String bio;

    private int numberOfFollower;

    private int numberOfFollowing;

}
