package com.example.until;

import com.example.bean.HobbyInfo;

import java.util.List;
import java.util.UUID;

public class GlobalUntil {
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
    public static String gethobbyInfo(List<HobbyInfo> hobbyInfos) {
        String hobbyInfo = "";
        for (HobbyInfo hobbyInfo1 : hobbyInfos) {
            hobbyInfo += hobbyInfo1.getHobby() + ",";
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(hobbyInfo)) {

            hobbyInfo = hobbyInfo.substring(0,hobbyInfo.length()-1);
        }
        return hobbyInfo;

    }
}
