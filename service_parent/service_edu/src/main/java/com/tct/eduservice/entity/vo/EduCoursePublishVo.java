package com.tct.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程发布页面显示实体
 */
@Data
public class EduCoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示

}
