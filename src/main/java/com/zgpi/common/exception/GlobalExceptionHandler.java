package com.zgpi.common.exception;

import com.zgpi.common.VO.ResultVO;
import com.zgpi.common.utils.JsonUtil;
import com.zgpi.common.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        ResultVO resultVO = ResultVOUtil.error(-1, "系统繁忙，请稍后再试");
        //如果是业务逻辑异常，返回具体的错误码与提示信息
        if (e instanceof AppException) {
            AppException logicException = (AppException) e;
            resultVO.setCode(logicException.getCode());
            resultVO.setMsg(logicException.getMessage());
        } else {
            //对系统级异常进行日志记录
            log.error("系统异常:" + e.getMessage(), e);
        }
        return JsonUtil.toJsonString("retMsg",resultVO);
    }
}
