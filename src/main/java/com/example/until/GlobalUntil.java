package com.example.until;

import java.util.UUID;

public class GlobalUntil {
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
}
