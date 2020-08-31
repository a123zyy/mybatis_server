package com.example.controller;


import com.example.bean.LabelInfo;
import com.example.pojo.LabelInfoDto;
import com.example.service.LabelInfoService;
import com.example.until.GlobalnumInfo;
import com.example.until.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/web/LabelInfo")
@RestController
@Api(value = "/LabelInfoController", tags = {"标签"})
@Slf4j
public class LabelInfoController {

    @Autowired
    private LabelInfoService labelInfoService;

    @RequestMapping(value = "/LabelInfo",method = RequestMethod.GET)
    public Result getLabelInfoAll(){
        List<LabelInfo> labelInfos = labelInfoService.findAllByStatus(GlobalnumInfo.IS_ASABLE.Key);
       List<LabelInfoDto> labelInfoDtos = labelInfos.stream().map(item->{
           LabelInfoDto labelInfoDto =new LabelInfoDto();
           BeanUtils.copyProperties(item,labelInfoDto);
           return labelInfoDto;
       }).collect(Collectors.toList());
       return Result.success(labelInfoDtos);
    }


}
