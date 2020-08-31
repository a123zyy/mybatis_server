package com.example.adminController;

import com.example.bean.HobbyInfo;
import com.example.bean.LabelInfo;
import com.example.pojo.HobbyInfoDto;
import com.example.pojo.LabelInfoDto;
import com.example.service.HobbyInfoService;
import com.example.service.LabelInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin/label/")
public class LabelController {
    @Autowired
    public LabelInfoService labelInfoService;


    @RequestMapping(value = "labelList", method = RequestMethod.GET)
    public Result getAll() {
        List<LabelInfo> labelInfos = labelInfoService.findAll();
        List<LabelInfoDto> labelInfoDtos = labelInfos.stream().map(item -> {
            LabelInfoDto labelInfoDto = new LabelInfoDto();
            BeanUtils.copyProperties(labelInfoDto, item);
            return labelInfoDto;
        }).collect(Collectors.toList());
        return Result.success(labelInfoDtos);

    }

    @RequestMapping(value = "label", method = RequestMethod.POST)
    public Result hobbyInsert(@RequestBody LabelInfoDto labelInfoDto) {
        if (StringUtils.isEmpty(labelInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(labelInfoDto.getLable())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        LabelInfo labelInfo = new LabelInfo();
        BeanUtils.copyProperties(labelInfoDto, labelInfo);
        return Result.success(labelInfoService.insertSelective(labelInfo));

    }

    @RequestMapping(value = "label", method = RequestMethod.PUT)
    public Result LabelUpdate(@RequestBody LabelInfoDto labelInfoDto) {
        if (StringUtils.isEmpty(labelInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(labelInfoDto.getLable())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        LabelInfo labelInfo = new LabelInfo();
        BeanUtils.copyProperties(labelInfoDto, labelInfo);
        return Result.success(labelInfoService.updateByPrimaryKeySelective(labelInfo));

    }

    @GetMapping(value = "label/{id}/{status}")
    public Result labelDelete(@PathVariable int id, @PathVariable int status) {
        return Result.success(labelInfoService.labelDelete(id, status));

    }

}
