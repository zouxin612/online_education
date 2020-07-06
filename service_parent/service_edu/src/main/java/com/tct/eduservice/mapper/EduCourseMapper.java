package com.tct.eduservice.mapper;

import com.tct.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tct.eduservice.entity.vo.EduCoursePublishVo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    EduCoursePublishVo selectCoursePublishVoById(String id);
}
