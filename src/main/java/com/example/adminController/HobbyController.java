package com.example.adminController;

import com.example.bean.HobbyInfo;
import com.example.pojo.HobbyInfoDto;
import com.example.service.HobbyInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/admin/user/")
public class HobbyController {
    @Autowired
    public HobbyInfoService hobbyInfoService;


    @RequestMapping(value = "hobbyList", method = RequestMethod.GET)
    public Result getAll() {
        return Result.success(hobbyInfoService.getAll());

    }

    @RequestMapping(value = "hobby", method = RequestMethod.POST)
    public Result hobbyInsert(@RequestBody HobbyInfoDto hobbyInfoDto) {
        if (StringUtils.isEmpty(hobbyInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(hobbyInfoDto.getHobby())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        HobbyInfo hobbyInfo = new HobbyInfo();
        BeanUtils.copyProperties(hobbyInfoDto, hobbyInfo);
        return Result.success(hobbyInfoService.insertSelective(hobbyInfo));

    }

    @RequestMapping(value = "hobby", method = RequestMethod.PUT)
    public Result hobbyUpdate(@RequestBody HobbyInfoDto hobbyInfoDto) {
        if (StringUtils.isEmpty(hobbyInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(hobbyInfoDto.getHobby())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        HobbyInfo hobbyInfo = new HobbyInfo();
        BeanUtils.copyProperties(hobbyInfoDto, hobbyInfo);
        return Result.success(hobbyInfoService.updateByPrimaryKeySelective(hobbyInfo));

    }

    @RequestMapping(value = "hobby/{id}/{status}", method = RequestMethod.GET)
    public Result hobbyDelete(@PathVariable int id, @PathVariable int status) {
        return Result.success(hobbyInfoService.hobbyDelete(id, status));

    }


}
