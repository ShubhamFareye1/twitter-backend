package com.groupC.twitter.dto;
import lombok.Data;
import java.util.Date;

@Data
public class CommentDto {
    private long commentId;

    private String commentText;

    private String image;

    private long userId;

    private long tweetId;

    private Date commentedAt;
}
