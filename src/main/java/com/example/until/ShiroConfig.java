package com.example.until;

import com.example.until.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
//之前没有加的时候没有扫描到 且用到@bean要用@Configuration
@Configuration
public class ShiroConfig {

    @Bean(name = "bean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWeb") DefaultWebSecurityManager defaultWebSecurityManager){
        System.out.println("执行了getShiroFilterFactoryBean");
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
//        anon: 无需认证就可以访问
//        authc:认证才能访问
//        user:必须有记住我功能才能用
//        perms:拥有对某个资源的权限才能用
//        role:有权限才能用
        Map<String,String> map =new HashMap();


        bean.setFilterChainDefinitionMap(map);
        return bean;
    }


    //把用户的信息存储到defaultWeb Qualifier合格者
    @Bean(name = "defaultWeb")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        System.out.println("执行了getDefaultWebSecurityManager");

        DefaultWebSecurityManager defaultWeb = new DefaultWebSecurityManager();
        defaultWeb.setRealm(userRealm);
        return defaultWeb;

    };

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
