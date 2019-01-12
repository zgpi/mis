package com.zgpi.user.controller;

import com.zgpi.common.utils.JsonUtil;
import com.zgpi.common.utils.ResultVOUtil;
import com.zgpi.user.domain.User;
import com.zgpi.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return JsonUtil.toJsonString("retMsg",ResultVOUtil.success());
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/modUser")
    public String modUser(User user){
        userService.modUser(user);
        return JsonUtil.toJsonString("retMsg",ResultVOUtil.success());
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping("/delUser")
    public String delUser(String userId){
        userService.delUser(userId);
        return JsonUtil.toJsonString("retMsg",ResultVOUtil.success());
    }
}
