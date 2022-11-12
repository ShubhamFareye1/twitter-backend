package com.groupC.twitter.service;
import com.groupC.twitter.model.User;

import java.util.List;

public interface UserService {
    public User getUser(long userId);
    public User getUserByUserName(String userName);
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(long userId);
    public void updateUserData(User user);

    public boolean addFollower(long followerId, long userId);

    public boolean removeFollower(long followerId, long userId);

    public List<User> getFollowers(long userId);

    public List<User> getFollowings(long userId);

}
