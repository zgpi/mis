package com.zgpi.common.exception;

import com.zgpi.common.enums.ResultEnum;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

    private Integer code;

    public AppException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public AppException(String message) {
        super(message);
        this.code = 0;
    }

    public AppException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
