package com.groupC.twitter.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true, nullable = false, length = 30)
    private String userName;

    @Column(name = "full_name",nullable = false,length = 50)
    private String name;

    private String password;

    private Date dob;

    @CreationTimestamp
    private  Date createdAt;
//    @Column(columnDefinition = "integer default 1")

    @Column(columnDefinition = "integer default 1")
    private Integer isVerified=1;

    @Column(columnDefinition = "boolean default false",nullable = false)
    private boolean roles;

    private String avatar;

    private String bannerImage;

    private String bio;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int numberOfFollower;

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int numberOfFollowing;

    @ElementCollection
    private Map<Long, Date> follower = new HashMap<>();

    @ElementCollection
    private Map<Long, Date> following = new HashMap<>();


    @OneToMany(mappedBy = "createdUser",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonIgnore
    private List<Tweet> tweets = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Messages> sendermsgs = new ArrayList<>();

    @OneToMany(mappedBy = "reciever",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Messages> recievermsgs = new ArrayList<>();

    public void setFollower(final long userId) {
        follower.put(userId, new Date());
    }

    public void setFollowing(final long userId) {
        following.put(userId, new Date());
    }

    public void removeFollower(final long userId) {
        follower.remove(userId);
    }

    public void removeFollowing(final long userId) {
        following.remove(userId);
    }
}
