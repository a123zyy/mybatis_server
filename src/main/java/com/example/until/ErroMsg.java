package com.example.until;

public class ErroMsg extends Throwable {
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ErroMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public ErroMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ErroMsg() {
    }

    //通用的错误码
    public static ErroMsg SUCCESS = new ErroMsg(0, "success");
    public static ErroMsg SERVER_ERROR = new ErroMsg(500100, "服务端异常");
    public static ErroMsg BIND_ERROR = new ErroMsg(500101, "参数校验异常：%s");
    public static ErroMsg ACCESS_LIMIT_REACHED= new ErroMsg(500104, "访问高峰期，请稍等！");
    //登录模块 5002XX
    public static ErroMsg SESSION_ERROR = new ErroMsg(500210, "Session不存在或者已经失效");
    public static ErroMsg PASSWORD_EMPTY = new ErroMsg(500211, "登录密码不能为空");
    public static ErroMsg LOGIN_EMPTY = new ErroMsg(5002121, "登录密码不能为空");
    public static ErroMsg MOBILE_EMPTY = new ErroMsg(500212, "手机号不能为空");
    public static ErroMsg MOBILE_ERROR = new ErroMsg(500213, "手机号格式错误");
    public static ErroMsg MOBILE_NOT_EXIST = new ErroMsg(500214, "手机号不存在");
    public static ErroMsg PASSWORD_ERROR = new ErroMsg(500215, "密码错误");
    public static ErroMsg PRIMARY_ERROR = new ErroMsg(500216, "主键冲突");
    public static ErroMsg NAMED_REPEAT_ERROR = new ErroMsg(500217, "该账户已被占用");
    public static ErroMsg PARAMETER_MINUS_ERROR = new ErroMsg(500218, "参数为负");
    public static ErroMsg NAMED_NULL_ERROR = new ErroMsg(500219, "名字为空");
    public static ErroMsg REDIS_NULL_ERROR = new ErroMsg(5002120, "redis出现问题");
    public static ErroMsg Nickname_length_ERROR = new ErroMsg(5002122, "昵称太长了");
    public static ErroMsg possword_length_ERROR = new ErroMsg(5002123, "密码太长了");
    public static ErroMsg possword_SHORT_ERROR = new ErroMsg(5002124, "密码太短了");
    public static ErroMsg PARAMER_NULL_ERROR = new ErroMsg(5002125, "参数为null");
    public static ErroMsg PARAMER_LENGTH_ERROR = new ErroMsg(5002126, "参数过长了");
    public static ErroMsg LIKE__REPEAT_ERROR = new ErroMsg(5002127, "重复动作");
    public static ErroMsg USER__LOGIN_ERROR = new ErroMsg(5002128, "用户未登录");















}
