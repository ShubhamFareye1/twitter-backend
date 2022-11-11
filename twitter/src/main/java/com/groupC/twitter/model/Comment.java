package com.groupC.twitter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class Comment {

    private long commentId;

    private String commentText;

    private long imageId;

    private long userId;

    private long tweetId;

    private Date commentedAt;

}
