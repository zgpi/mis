package com.zgpi.common.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(1, "成功"),
    ERROR(-1, "错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
