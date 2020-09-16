package com.example.config;

import org.apache.catalina.util.RequestUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zyy
 */
public class BlogInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    public BlogInterceptor() {
        // TODO Auto-generated constructor stub
        System.out.println("构造方法执行");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getServletPath().equals("/web/PostInfo/likes") || request.getServletPath().equals("/web/CommentInfo/CommentInfo") || request.getServletPath().equals("/web/PostInfo/List/Oneid")) {
            if (null == request.getHeader("token") || null == request.getHeader("uid")) {
               throw new Error();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("执行完毕后执行");
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
