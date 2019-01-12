package com.zgpi.interceptor;

import com.zgpi.common.exception.AppException;
import com.zgpi.common.utils.CookieUtil;
import com.zgpi.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登录拦截器
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("拦截URL：" + httpServletRequest.getRequestURI());
        log.info("---------------------开始进入请求地址拦截----------------------------");
        String cookie = CookieUtil.readLoginToken(httpServletRequest);
        log.info("cookie:" + cookie);
        if(StringUtils.isEmpty(cookie)){//没有cookie
            throw new AppException("请先登录！");
        }
        Object token = RedisUtil.get("SESSIONID:" + cookie);
        log.info("token:" + token);
        if(StringUtils.isEmpty(token)){//redis中没有该token
            throw new AppException("请先登录！");
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后的操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------拦截完成之后的操作-------------------------0");
    }

}
