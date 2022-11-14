//package com.groupC.twitter.model;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "images")
//public class Image {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//   private long imageId;
//
//    private String imageUrl;
//    private long tweetId;
//    private long commentId;
//
//    @ManyToOne(targetEntity = Tweet.class)
//    @JoinColumn
//    private Tweet tweet;
//
//    @ManyToOne(targetEntity = Comment.class)
//    @JoinColumn
//    private Comment comment;
//
//}
