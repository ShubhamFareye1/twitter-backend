package com.groupC.twitter.model;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
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

    @CreationTimestamp
    private Date messageDate;

    private long senderId;

    private long recieverId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
    private User sender;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(updatable = false,nullable = false)
    private User reciever;
}
