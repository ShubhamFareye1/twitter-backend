package com.groupC.twitter.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class User {

    private long userId;

    private String userName;

    private String name;

    private String password;

    private Date dob;

    private  Date createdAt;

    private Boolean isVerified;

}
