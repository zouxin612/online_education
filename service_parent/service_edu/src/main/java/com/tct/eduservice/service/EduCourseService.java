package com.tct.eduservice.service;

import com.tct.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tct.eduservice.entity.vo.EduCourseInfoVo;
import com.tct.eduservice.entity.vo.EduCoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
public interface EduCourseService extends IService<EduCourse> {

    // 添加课程信息
    String addCourseInfo(EduCourseInfoVo courseInfoVo);

    // 查询课程信息
    EduCourseInfoVo getCourseInfoById(String id);

    // 修改课程信息
    boolean updateCourseInfo(EduCourseInfoVo courseInfoVo);

    // 查询发布信息
    EduCoursePublishVo getCoursePublishVoById(String id);

    // 根据课程id发布信息
    boolean publishCourseById(String id);
}
