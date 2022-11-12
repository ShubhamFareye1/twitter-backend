package com.groupC.twitter.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long imageId;

   private String imageUrl;

    @ManyToOne(targetEntity = Tweet.class)
    @JoinColumn
    private long tweetId;

    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn
    private long commentId;

}
