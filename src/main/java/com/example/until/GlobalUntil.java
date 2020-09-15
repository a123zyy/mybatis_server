package com.example.until;

import com.example.bean.HobbyInfo;
import org.apache.catalina.util.RequestUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GlobalUntil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    private GlobalUntil() {}


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
    private static final DateTimeFormatter DATE_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd HH:mm:ss")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0).toFormatter();


    public static String dateFormat(Date timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return DATE_FORMAT.format(
                LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
    }

    /**
         * request的全部参数转换成map
         * @param request
         * @return
         */
        @SuppressWarnings("unchecked")
        public static Map<String, String[]> getMap(HttpServletRequest request){

            return request.getParameterMap();
        }


        /**
         * request的部分参数转换成map：
         * 根据的keys指定的的Key从request取出值放到Map中
         * @param request
         * @param keys 需要转的key,多个key用豆号隔开
         * @return
         */
        public static Map<String,Object> getMap(HttpServletRequest request, String keys){
            String[] split = keys.split(",");
            Map<String,Object> map = new HashMap<String, Object>(split.length);
            for (String key : split) {
                map.put(key, request.getParameter(key));
            }
            return map;
        }



        /**
         * 将map转换成bean对象：
         * 根据Map的keyValues创建Bean对象
         * @param theClass
         * @param keyValues
         * @return
         */
        public static <T> T getBean(Class<T> theClass,Map<String,Object> keyValues){
            T bean = null;
            try {
                bean = theClass.newInstance();
                BeanUtils.populate(bean, keyValues);
            } catch (Exception e) {
                LOGGER.error("map转换成bean对象出错", e);
            }
            return bean;
        }
    }

