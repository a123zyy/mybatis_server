package com.example.adminController;

import com.example.service.UserInfoService;
import com.example.until.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/user/")
public class UserController {
    @Autowired
    public UserInfoService userInfoService;

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    public Result getAllUser() {
        return Result.success("");
    }
}
