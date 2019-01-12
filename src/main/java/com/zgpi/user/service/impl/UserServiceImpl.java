package com.zgpi.user.service.impl;

import com.zgpi.common.exception.AppException;
import com.zgpi.common.utils.JsonUtil;
import com.zgpi.common.utils.RedisUtil;
import com.zgpi.user.dao.UserMapper;
import com.zgpi.user.domain.User;
import com.zgpi.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(String userId, String userPwd) {
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(userPwd)){
            throw new AppException("用户名或密码为空！");
        }
        User user = this.findByUserId(userId);
        if(user == null){
            throw new AppException("用户名不存在！");
        }
        if(!userPwd.equals(user.getUserPwd())) {
            userPwd = DigestUtils.md5DigestAsHex(userPwd.getBytes());
            if (!userPwd.equals(user.getUserPwd())) {
                throw new AppException("密码错误！");
            }
        }else{
            userPwd = DigestUtils.md5DigestAsHex(userPwd.getBytes());
            this.md5UserPwd(userId, userPwd);
        }
        //登录成功
        String token = UUID.randomUUID().toString();//生成的uuid必不重复
        user.setUserPwd("");
        RedisUtil.set("SESSIONID:" + token, JsonUtil.toJson(user));
        return token;
    }

    @Override
    public void md5UserPwd(String userId, String userPwd) {
        userMapper.md5UserPwd(userId, userPwd);
    }

    @Override
    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    @Override
    public void addUser(User user) {
        if(user == null){
            throw new AppException("参数传递错误！");
        }
        User db_user = this.findByUserId(user.getUserId());
        if(db_user != null){
            throw new AppException("用户名已存在，请修改后再提交！");
        }
        userMapper.addUser(user);
    }

    @Override
    public void modUser(User user) {
        if(user == null){
            throw new AppException("参数传递错误！");
        }
        User db_user = this.findByUserId(user.getUserId());
        if(db_user == null){
            throw new AppException("用户不存在！");
        }
        userMapper.modUser(user);
    }

    @Override
    public void delUser(String userId) {
        if(StringUtils.isEmpty(userId)){
            throw new AppException("参数传递错误！");
        }
        userMapper.delUser(userId);
    }


}
