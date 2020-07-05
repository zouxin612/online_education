package com.tct.eduservice.controller;


import com.tct.commonutils.ResponseResult;
import com.tct.eduservice.entity.EduChapter;
import com.tct.eduservice.entity.tree.chapter.ChapterParent;
import com.tct.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Api(description = "章节信息管理")
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class EduChapterController {

    public static final String GET = "/get/{chapterId}";
    public static final String ADD_CHAPTER_INFO = "/addChapterInfo";
    public static final String GET_CHAPTER_INFO_TREE = "/getChapterInfoList/{courseId}";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete/{chapterId}";

    @Autowired
    public EduChapterService chapterService;

    @ApiOperation("根据课程id查找该课程下的章节信息")
    @GetMapping(GET_CHAPTER_INFO_TREE)
    public ResponseResult getChapterInfoTree(@ApiParam(name = "courseId",value = "课程id",required = true)
                                             @PathVariable String courseId){
        List<ChapterParent> chapterInfoTree = chapterService.getChapterInfoTree(courseId);
        if(null != chapterInfoTree && chapterInfoTree.size()>0){
            return ResponseResult.ok().data("items",chapterInfoTree);
        }else{
            return ResponseResult.error().message("查询失败");
        }
    }

    @ApiOperation("根据章节id查询章节信息")
    @GetMapping(GET)
    public ResponseResult getChapterInfoById(@ApiParam(name = "chapterId",value = "章节id",required = true)
                                             @PathVariable String chapterId){
        EduChapter chapter = chapterService.getById(chapterId);
        if(null != chapter){
            return ResponseResult.ok().data("chapter",chapter);
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation("添加章节信息")
    @PostMapping(ADD_CHAPTER_INFO)
    public ResponseResult addChapterInfo(@ApiParam(name = "chapter",value = "章节信息",required = true)
                                         @RequestBody EduChapter chapter){
        boolean save = chapterService.save(chapter);
        if(save){
            return ResponseResult.ok().message("章节添加成功").data("chapter",chapter);
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation("修改章节信息")
    @PutMapping(UPDATE)
    public ResponseResult updateChapterInfoById(@ApiParam(name = "chapter",value = "章节信息",required = true)
                                                    @RequestBody EduChapter chapter){
        boolean update = chapterService.updateById(chapter);
        if(update){
            return ResponseResult.ok().message("修改章节信息成功").data("chapter",chapter);
        }else{
            return ResponseResult.error();
        }
    }

    @ApiOperation("删除章节信息")
    @DeleteMapping(DELETE)
    public ResponseResult deleteChapterInfoById(@ApiParam(name = "chapterId",value = "章节id",required = true)
                                                    @PathVariable String chapterId){
        int deleteCode = chapterService.deleteChapterInfoById(chapterId);
        if (0 == deleteCode){
            return ResponseResult.ok().message("删除成功");
        }else if(1 == deleteCode){
            return ResponseResult.error().message("该章节下有小节信息不能删除");
        }else {
            return ResponseResult.error().message("删除失败");
        }
    }

}

