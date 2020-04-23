package com.cqszw.demo.Service;

import com.cqszw.demo.Bean.User;
import com.cqszw.demo.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User getUserbyUsername(String username){return userMapper.getbyusername(username);}
    public User getUserbyId(int id){return  userMapper.getbyid(id);}
    public List<User> getAll(){return  userMapper.getAll();}
}
