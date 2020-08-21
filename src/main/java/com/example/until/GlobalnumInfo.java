package com.example.until;

public enum GlobalnumInfo {
    IS_ASABLE(1,"可用"),NO_ASABLE(0,"可用"),USERID(1,"全局默认uid");

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
