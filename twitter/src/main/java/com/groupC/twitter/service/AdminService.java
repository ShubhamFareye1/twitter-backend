package com.groupC.twitter.service;

import com.groupC.twitter.dto.UserDto;

import java.util.List;

public interface AdminService {

    public List<UserDto> getRequestBluetick();

    public boolean setBluetick(long userId,boolean resp);

}
