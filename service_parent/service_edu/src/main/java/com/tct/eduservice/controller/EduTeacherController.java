package com.tct.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.entity.EduTeacher;
import com.tct.eduservice.entity.vo.TeacherQueryVo;
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
@Api(description ="讲师管理")
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class EduTeacherController {

    public static final String GET_ALL = "/getAll";
    public static final String PAGE_LIMIT = "/{page}/{limit}";
    public static final String PAGE_LIMIT_WRAPPER = "wrapper/{page}/{limit}";
    public static final String INSERT_TEACHER = "/save";
    public static final String GET_BY_ID = "get/{id}";
    public static final String UPDATE_TEACHER = "/update/{id}";
    public static final String DELETE_BY_ID = "/delete/{id}";

    @Autowired
    public EduTeacherService eduTeacherService;


    @GetMapping(GET_ALL)
    @ApiOperation(value = "查询所有讲师列表")
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

    @ApiOperation("条件分页查询")
    @PostMapping(PAGE_LIMIT_WRAPPER)
    public ResponseResult getTeacherByPageAndWrapper(@PathVariable(required = true) Long page,
                                                     @PathVariable(required = true) Long limit,
                                                     @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        // 调用service层条件查询
        eduTeacherService.pageQuery(pageParam,teacherQueryVo);

        // 获取记录数
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("添加讲师信息")
    @PostMapping(INSERT_TEACHER)
    public ResponseResult insertTeacher(@RequestBody(required = true) @ApiParam("讲师信息实体") EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);

        if(save){
            return ResponseResult.ok().data("item",eduTeacher);
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(GET_BY_ID)
    public ResponseResult getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        return ResponseResult.ok().data("item", teacher);
    }

    @ApiOperation("修改讲师信息")
    @PutMapping(UPDATE_TEACHER)
    public ResponseResult updateTeacher(@RequestBody(required = true)
                                            @ApiParam("讲师信息实体") EduTeacher eduTeacher,
                                        @ApiParam(name = "id", value = "讲师ID", required = true)
                                        @PathVariable String id){
        eduTeacher.setId(id);
        boolean update = eduTeacherService.updateById(eduTeacher);

        if(update){
            return ResponseResult.ok();
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation("删除讲师信息")
    @DeleteMapping(DELETE_BY_ID)
    public ResponseResult deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                                @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag) {
            return ResponseResult.ok();
        } else {
            return ResponseResult.error();
        }
    }

}

