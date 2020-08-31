package com.example.adminController;

import com.example.pojo.UserInfoDto;
import com.example.service.UserInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/user/")
public class UserController {
    @Autowired
    public UserInfoService userInfoService;

    @RequestMapping(value = "UserList", method = RequestMethod.POST)
    public Result getAllUser(int pageSize, int pageNum) {
        return Result.success(userInfoService.findAllBy(pageSize, pageNum));
    }

    @RequestMapping(value = "user", method = RequestMethod.PUT)
    public Result getAllUser(@RequestBody UserInfoDto userInfoDto) {
        if (StringUtils.isEmpty(userInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(userInfoDto.getNickname())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        //查询名称是否重复
        if (userInfoService.countByNickname(userInfoDto.getNickname()) > 0) {
            return Result.error(ErroMsg.NAMED_REPEAT_ERROR);
        }
        if (StringUtils.isEmpty(userInfoDto.getAvatar())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        return Result.success(userInfoService.updateByPrimaryKeySelective(userInfoDto));
    }


}
