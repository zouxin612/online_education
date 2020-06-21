package com.tct.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tct.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tct.eduservice.entity.vo.TeacherQueryVo;
import com.tct.eduservice.mapper.EduTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author tct
 * @since 2020-05-05
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 定义条件查询函数
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQueryVo teacherQuery);
}
