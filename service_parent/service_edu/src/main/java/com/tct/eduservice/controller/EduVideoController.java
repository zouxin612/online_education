package com.tct.eduservice.controller;


import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.entity.EduVideo;
import com.tct.eduservice.entity.vo.EduVideoInfoVo;
import com.tct.eduservice.service.EduChapterService;
import com.tct.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Api(description = "课时小节信息管理")
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class EduVideoController {

    public static final String ADD_VIDEO_INFO = "/addVideoInfo";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{videoId}";
    public static final String GET = "/get/{videoId}";

    @Autowired
    public EduVideoService eduVideoService;

    @ApiOperation("添加小节信息")
    @PostMapping(ADD_VIDEO_INFO)
    public ResponseResult addVideoInfo(@RequestBody EduVideoInfoVo eduVideoInfoVo){
        boolean save = eduVideoService.saveVideoInfo(eduVideoInfoVo);
        if(save){
            return ResponseResult.ok().data("video",eduVideoInfoVo);
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation("编辑小节信息")
    @PutMapping(UPDATE)
    public ResponseResult updateVideoInfoById(@RequestBody EduVideoInfoVo eduVideoInfoVo){
        boolean update = eduVideoService.updateVideoInfo(eduVideoInfoVo);
        if(update){
            return ResponseResult.ok().data("video",eduVideoInfoVo);
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation("删除小节信息")
    @DeleteMapping(DELETE)
    public ResponseResult deleteById(@PathVariable String videoId){
        boolean remove = eduVideoService.removeById(videoId);
        if(remove){
            return ResponseResult.ok();
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation("根据课时小节id查询课时小节信息")
    @GetMapping(GET)
    public ResponseResult getVideoInfoById(@ApiParam(name = "videoId",value = "课时小节id",required = true)
                                           @PathVariable String videoId){
        EduVideoInfoVo videoInfoForm = eduVideoService.getVideoInfoFormById(videoId);
        if(null != videoInfoForm){
            return ResponseResult.ok().data("videoInfoForm", videoInfoForm);
        }else{
            return ResponseResult.error();
        }


    }

}

