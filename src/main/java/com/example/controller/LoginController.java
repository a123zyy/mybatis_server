package com.example.controller;

import com.example.bean.LoginInfo;
import com.example.bean.UserInfo;
import com.example.pojo.LoginDto;
import com.example.service.LoginInfoService;
import com.example.service.UserInfoService;
import com.example.until.ErroMsg;
import com.example.until.GlobalUntil;
import com.example.until.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RequestMapping("/login")
@RestController
@Api(value = "/login", tags = {"登录"})
@Slf4j
public class LoginController {
    private final static int DATA = 0x9fa5 - 0x4e00 + 1;
    @Value("${userInfo.avatar}")
    private String avatar;
    @Autowired
    public UserInfoService userInfoService;
    @Autowired
    public LoginInfoService loginInfoService;

    @RequestMapping(value = "/tologin",method = RequestMethod.POST)
    public Result getLogin(@RequestBody LoginDto loginDto){
        if (StringUtils.isEmpty(loginDto.getUsername())||StringUtils.isEmpty(loginDto.getPassword())){
            throw new UnknownAccountException("用户名或密码为空");
        }
        //账户密码
        if (loginDto.getType() == 1){
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                try {
                    currentUser.login(new UsernamePasswordToken(loginDto.getPassword(), loginDto.getPassword()));
                } catch (UnknownAccountException uae) {
                    log.info("There is no user with username of " + currentUser.getPrincipal());
                    throw new UnknownAccountException("用户名错误");
                } catch (IncorrectCredentialsException ice) {
                    log.info("Password for account " + currentUser.getPrincipal() + " was incorrect!");
                    throw new IncorrectCredentialsException("密码错误");
                } catch (LockedAccountException lae) {
                    log.info("The account for username " + currentUser.getPrincipal() + " is locked.  " +
                            "Please contact your administrator to unlock it.");
                    throw new LockedAccountException("该账户被封禁");
                } catch (AuthenticationException ae) {
                    log.info("系统错误！");
                    throw new AuthenticationException("该账户被封禁");
                }
            }
            currentUser = SecurityUtils.getSubject();
        } else if (loginDto.getType() == 2){
            //手机号
            return Result.success("");
        } else if (loginDto.getType() == 3){
            //第三方
            return Result.success("");
        }
        LoginInfo list = loginInfoService.selectByUserName(loginDto.getUsername());
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(loginDto,loginInfo);
        loginInfo.setLasttime(new Date());
        loginInfo.setId(list.getId());
        int update = loginInfoService.updateByPrimaryKeySelective(loginInfo);
        Map<String,Object> userMap =new HashMap<>();
        if (update > 0){
            userMap.put("token", GlobalUntil.getUUID());
            userMap.put("userInfo",userInfoService.selectByPrimaryKey(list.getUserid()));
            return Result.success(userMap);
        }

        return Result.error(ErroMsg.REDIS_NULL_ERROR);
    }
    //注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result register(@RequestBody LoginDto loginDto){
        if (StringUtils.isEmpty(loginDto.getUsername())|| StringUtils.isEmpty(loginDto.getPassword())){
            return Result.error(ErroMsg.LOGIN_EMPTY);
        }
        if (loginDto.getUsername().length() > 10){
            return Result.error(ErroMsg.Nickname_length_ERROR);
        }
        if (loginDto.getPassword().length() > 10){
            return Result.error(ErroMsg.possword_length_ERROR);
        }
        if (loginDto.getPassword().length() < 6){
            return Result.error(ErroMsg.possword_SHORT_ERROR);
        }
        if (loginInfoService.findByUsername(loginDto.getUsername())){
            return Result.error(ErroMsg.NAMED_REPEAT_ERROR);
        }

        LoginInfo loginInfo = new LoginInfo();
        UserInfo userInfo = new UserInfo();
        int num=(int)(Math.random()*9000)+1000;
        userInfo.setNickname(this.getRandomHan()+""+num);
        userInfo.setAvatar(avatar);
        userInfo.setComment("这个人什么都没留下~");
        int userid =  userInfoService.insertSelective(userInfo);

        BeanUtils.copyProperties(loginDto,loginInfo);
        loginInfo.setLasttime(new Date());
        loginInfo.setUserid(userid);
        return Result.success(loginInfoService.insert(loginInfo));
    }
    //获取主页的信息 查到本人的信息  id为1的一条
    public Result getToHome(){
        return Result.success("");
    }
    //随机生成汉字
    public char getRandomHan () {
        Random ran = new Random();
        return (char) (0x4e00 + ran.nextInt(DATA));
    }
}
