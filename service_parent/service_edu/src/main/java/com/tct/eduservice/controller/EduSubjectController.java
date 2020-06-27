package com.tct.eduservice.controller;


import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 课程分类管理模块
 * </p>
 *
 * @author tct
 * @since 2020-06-27
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    public EduSubjectService eduSubjectService;

    @ApiOperation("课程分类Excel数据导入")
    @PostMapping("/addSubject")
    public ResponseResult addSubject(@ApiParam(name = "excel文件",required = true)
                                     @RequestParam(required = true)MultipartFile file){

        // 调用service层数据处理
        boolean flag = eduSubjectService.saveSubject(file, eduSubjectService);
        if(flag){
            return ResponseResult.ok().message("导入数据成功");
        }else{
            return ResponseResult.error().message("导入数据失败");
        }
    }

}

