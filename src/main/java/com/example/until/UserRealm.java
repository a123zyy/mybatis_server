package com.example.until;

import com.example.bean.LoginInfo;
import com.example.service.LoginInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.security.auth.login.LoginException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class UserRealm extends AuthorizingRealm {
    //根据用户名查密码
   @Autowired
   public LoginInfoService loginInfoService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("执行了授权doGetAuthorizationInfo");
        log.info(principals.toString()+"这是principals");
        Object login = getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (StringUtils.isEmpty(login)){
           throw  new AuthenticationException("");
        }
//        JSONObject json = (JSONObject) JSON.toJSON(login);
//        info.addRole((String) json.get("username"));
        info.addStringPermission("所有的菜单");
        return info;
    }



    //登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("执行了doGetAuthenticationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        if (StringUtils.isEmpty(usernamePasswordToken.getUsername())){
            throw new UnknownAccountException("账户不能为空");
        }
        if (StringUtils.isEmpty(usernamePasswordToken.getPassword())){
            throw new UnknownAccountException("密码不能为空");
        }
        LoginInfo loginInfo =  loginInfoService.selectByUserName(usernamePasswordToken.getUsername());
        if (Objects.isNull(loginInfo)){
            throw new NullPointerException("该用户没有注册");
        }
//        usernamePasswordToken.setRememberMe(true);
        return new SimpleAuthenticationInfo(loginInfo.getUsername(),loginInfo.getPassword(),getName());
    }


}
