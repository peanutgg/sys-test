package com.sys.test.service.a.service;

import com.sys.test.service.a.entity.UserInfo;
import com.sys.test.service.a.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.LinkOption;
import java.util.List;

@Component
public class UserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    public int addUser(String name){
       return userInfoMapper.insert(new UserInfo(name));
    }

    public List<UserInfo> getUser(Integer id) {
        return userInfoMapper.selectUsers(id);
    }
}
