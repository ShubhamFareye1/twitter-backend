package com.groupC.twitter.service;
import com.groupC.twitter.dto.BookmarkDto;
import com.groupC.twitter.dto.UserDto;
import com.groupC.twitter.model.Bookmark;
import com.groupC.twitter.model.User;

import java.util.List;

public interface UserService {
    public UserDto getUser(long userId);
    public UserDto getUserByUserName(String userName);
    public UserDto addUser(UserDto userDto);
    public UserDto updateUser(UserDto userDto);
    public void deleteUser(long userId);
    public boolean addFollower(long followerId, long userId);
    public boolean removeFollower(long followerId, long userId);
    public List<UserDto> getFollowers(long userId);
    public List<UserDto> getFollowings(long userId);

    public List<BookmarkDto> getBookmarks(long userId);

    public BookmarkDto addBookmark(BookmarkDto bookmarkDto);

}
