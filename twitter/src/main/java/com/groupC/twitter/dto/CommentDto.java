package com.groupC.twitter.dto;
import com.groupC.twitter.model.User;
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

    private User user;
}
