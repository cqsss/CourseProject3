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
    public List<User> getAll(){return  userMapper.getAll();}
    public int insertUser(User user){return  userMapper.insertUser(user);}
    public int updateUser(User user,User old_user){
        return  userMapper.updateUser(user.getUsername(),user.getPassword(),user.isIs_manager(),
                user.getName(),user.getAge(),user.getSex(),user.getTelephone(),
                user.getIntroduce(),old_user.getUsername());
    }
    public  int deleteUser(String username){
        return  userMapper.deleteUser(username);
    }
    public boolean searchUser(String username){
        if(userMapper.getbyusername(username)!=null){
            return  true;
        }
        else {
            return  false;
        }
    }
}
