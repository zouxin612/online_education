package com.tct.eduservice.controller;


import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.entity.vo.EduCourseInfoVo;
import com.tct.eduservice.entity.vo.EduCoursePublishVo;
import com.tct.eduservice.service.EduChapterService;
import com.tct.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程管理模块，发布课程信息功能实现
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Api(description = "发布课程信息管理")
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {

    public static final String POST_COURSE_INFO = "/addCourseInfo";
    public static final String GET_COURSE_BY_ID = "/getCourseInfoById/{id}";
    public static final String UPDATE_COURSE_INFO = "/updateCourseInfo";
    public static final String DELETE_COURSE_INFO = "/deleteCourseInfo";
    public static final String GET_PUBLISH_INFO = "/getCoursePublishInfo/{id}";
    public static final String PUBLISH_COURSE_INFO = "/publishCourseInfo/{id}";

    @Autowired
    public EduCourseService courseService;

    @ApiOperation("添加课程信息")
    @PostMapping(POST_COURSE_INFO)
    public ResponseResult addCourseInfo(@ApiParam(value = "课程信息接收实体",required = true)
                                        @RequestBody EduCourseInfoVo courseInfoVo){
        String cid = courseService.addCourseInfo(courseInfoVo);
        if(StringUtils.isEmpty(cid)){
            return ResponseResult.error().message("添加课程信息数据失败");
        }else {
            return ResponseResult.ok().data("cid",cid);
        }
    }

    @ApiOperation("根据课程id获取课程信息")
    @GetMapping(GET_COURSE_BY_ID)
    public ResponseResult get(@ApiParam(name = "id",value = "课程id",required = true)
                                            @PathVariable String id){
        EduCourseInfoVo courseInfoVo = courseService.getCourseInfoById(id);
        if(null != courseInfoVo){
            return ResponseResult.ok().data("items",courseInfoVo);
        }else{
            return ResponseResult.error().message("没有查询到对应的课程信息");
        }
    }

    @ApiOperation("修改课程信息")
    @PutMapping(UPDATE_COURSE_INFO)
    public ResponseResult updateCourseInfo(@RequestBody EduCourseInfoVo courseInfoVo){
        boolean update = courseService.updateCourseInfo(courseInfoVo);
        if(update){
            return ResponseResult.ok();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation("查询要发布的信息")
    @GetMapping(GET_PUBLISH_INFO)
    public ResponseResult getCoursePublishInfoById(@ApiParam(name = "id",value = "课程id",required = true)
                                                   @PathVariable String id){
        EduCoursePublishVo publishVo = courseService.getCoursePublishVoById(id);
        if(null != publishVo){
            return ResponseResult.ok().data("item",publishVo);
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation("根据课程id发布课程")
    @PutMapping(PUBLISH_COURSE_INFO)
    public ResponseResult publishCourseInfo( @ApiParam(name = "id", value = "课程ID", required = true)
                                                 @PathVariable String id){
        boolean b = courseService.publishCourseById(id);
        if(b){
            return ResponseResult.ok();
        }else {
            return ResponseResult.error();
        }
    }

}

