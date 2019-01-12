package com.zgpi.user.dao;

import com.zgpi.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User login(@Param("user_id") String userId, @Param("user_pwd") String userPwd);

    void md5UserPwd(@Param("user_id") String userId, @Param("user_pwd") String userPwd);

    User findByUserId(String userId);

    void addUser(User user);

    void modUser(User user);

    void delUser(String userId);

}
