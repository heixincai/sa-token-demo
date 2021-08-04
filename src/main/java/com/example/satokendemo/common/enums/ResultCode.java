package com.example.satokendemo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAILURE(400, "失败"),

    INVALID_ARGUMENT(300, "参数错误"),

    UN_LOGIN(401, "未登录"),
    USER_UNAUTHORIZED(402, "用户名或密码不正确"),
    UNAUTHORIZED(403, "未认证或Token失效"),
    NOT_FOUND(404, "接口不存在"),
    FIRST_LOGIN(405, "首次登录，请修改密码。"),
    LAST_UPDATE_PWD_CYCLE(406, "您已长期未修改密码，请修改密码后登录。"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String desc;
}
