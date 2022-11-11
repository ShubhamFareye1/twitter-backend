package com.groupC.twitter.dto;
import lombok.Data;
import java.util.Date;

@Data
public class CommentDto {
    private long commentId;

    private String commentText;

    private long imageId;

    private long userId;

    private long tweetId;

    private Date commentedAt;
}
