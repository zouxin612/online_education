package com.tct.eduservice.controller;


import com.tct.eduservice.entity.EduTeacher;
import com.tct.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public EduTeacherService eduTeacherService;


    @GetMapping(GET_ALL)
    @ApiOperation(value = "所有讲师列表")
    public List<EduTeacher> getAll(){
        return eduTeacherService.list(null);
    }
}

