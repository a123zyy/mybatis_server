package com.example.until;

public enum GlobalnumInfo {
    IS_ASABLE(1,"可用"),NO_ASABLE(0,"不可用")
    ,USERID(1,"全局默认uid"),IS_PASSWORD(1,"密码登录"),
    IS_MOBILE(2,"手机号验证码"),IS_THIRED(3,"第三方登录");

    public final int Key;

    GlobalnumInfo(int key, String value) {
        Key = key;
        Value = value;
    }

    private final String Value;


    @Override
    public String toString() {
        return super.toString();



    }
}
