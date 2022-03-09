package com.shf.service;

import com.shf.mapper.UserMapper;
import com.shf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {
    public User queryUserByName(String name);


}
