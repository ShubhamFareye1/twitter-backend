package com.groupC.twitter.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "messages")
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long messageId;

    @Column(nullable = false)
   private String text;

   @CreatedDate
   private Date messageDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
   private long senderId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
   private long recieverId;
}
