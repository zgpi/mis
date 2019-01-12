package com.zgpi.user.controller;

import com.zgpi.common.utils.CookieUtil;
import com.zgpi.common.utils.JsonUtil;
import com.zgpi.common.utils.ResultVOUtil;
import com.zgpi.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(@RequestParam("userId") String userId,
                      @RequestParam("userPwd") String userPwd,
                        HttpServletResponse response){
        String token = userService.login(userId, userPwd);
        CookieUtil.writeLoginToken(response, token);
        return JsonUtil.toJsonString("retMsg",ResultVOUtil.success(),"token",token);
    }
}
