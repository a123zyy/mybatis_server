package com.example.adminController;


import com.example.bean.CommentInfo;
import com.example.pojo.CommentInfoDto;
import com.example.service.CommentInfoService;
import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyy
 */
@RequestMapping("/admin/user/")
public class CommentController {
    @Autowired
    public CommentInfoService commentInfoService;

    @RequestMapping(value = "Comments", method = RequestMethod.GET)
    public Result getALL(int pageSize, int PageNum) {
        PageHelper.startPage(PageNum, pageSize);
        List<CommentInfo> commentInfos = commentInfoService.finAll();
        List<CommentInfoDto> commentInfoDtos = commentInfos.stream().map(item -> {
            CommentInfoDto commentInfoDto = new CommentInfoDto();
            BeanUtils.copyProperties(item, commentInfoDto);
            return commentInfoDto;
        }).collect(Collectors.toList());
        return Result.success(commentInfoDtos);
    }


}
