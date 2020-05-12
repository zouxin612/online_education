package com.tct.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.entity.EduTeacher;
import com.tct.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author tct
 * @since 2020-05-05
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
@Api(description ="讲师管理")
public class EduTeacherController {

    public static final String GET_ALL = "/getAll";
    public static final String PAGE_LIMIT = "/{page}/{limit}";

    @Autowired
    public EduTeacherService eduTeacherService;


    @GetMapping(GET_ALL)
    @ApiOperation(value = "所有讲师列表")
    public ResponseResult getAll(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return ResponseResult.ok().data("items",list);
    }

    @ApiOperation("分页查询讲师列表")
    @GetMapping(PAGE_LIMIT)
    public ResponseResult getTeacherByPage(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                                           @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page,limit);
        eduTeacherService.page(pageParam,null);
        List<EduTeacher> list = pageParam.getRecords();
        long total = pageParam.getTotal();
        //long total = eduTeacherService.list(null).size();
        return ResponseResult.ok().data("total",total).data("rows",list);
    }
}

