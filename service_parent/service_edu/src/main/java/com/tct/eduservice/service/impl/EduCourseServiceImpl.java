package com.tct.eduservice.service.impl;

import com.tct.eduservice.entity.EduCourse;
import com.tct.eduservice.entity.EduCourseDescription;
import com.tct.eduservice.entity.vo.EduCourseInfoVo;
import com.tct.eduservice.mapper.EduCourseDescriptionMapper;
import com.tct.eduservice.mapper.EduCourseMapper;
import com.tct.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    public EduCourseMapper courseMapper;

    @Autowired
    public EduCourseDescriptionMapper descriptionMapper;

    @Override
    public String addCourseInfo(EduCourseInfoVo courseInfoVo) {
        // 添加课程信息
        // 创建课程信息对象
        EduCourse eduCourse = new EduCourse();
        eduCourse.setIsDeleted(0);
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        // 将对象数据添加到数据库中
        int value = courseMapper.insert(eduCourse);
        if(value > 0){
            // 表示有数据成功添加到课程信息表中
            // 获取课程id
            String cid = eduCourse.getId();
            // 创建课程简介表
            EduCourseDescription description = new EduCourseDescription();
            description.setDescription(courseInfoVo.getDescription());
            description.setId(cid);
            // 将数据添加到数据库中
            int valueDes = descriptionMapper.insert(description);
            if(valueDes > 0){
                return cid;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public EduCourseInfoVo getCourseInfoById(String id) {
        // 创建返回信息对象
        EduCourseInfoVo eduCourseInfoVo = new EduCourseInfoVo();
        // 查询课程信息
        EduCourse eduCourse = courseMapper.selectById(id);
        if(null == eduCourse){
            return null;
        }else {
            BeanUtils.copyProperties(eduCourse,eduCourseInfoVo);
        }
        // 查询课程描述信息
        EduCourseDescription description = descriptionMapper.selectById(id);
        if(null == description){
            return null;
        }else{
            eduCourseInfoVo.setDescription(description.getDescription());
        }
        return eduCourseInfoVo;
    }

    @Override
    public boolean updateCourseInfo(EduCourseInfoVo courseInfoVo) {
        // 判断课程信息是否为空
        if(null == courseInfoVo){
            return false;
        }else {
            // 修改课程基本信息
            EduCourse course = new EduCourse();
            BeanUtils.copyProperties(courseInfoVo,course);
            int row = courseMapper.updateById(course);
            if(row > 0){
                // 修改课程描述信息
                EduCourseDescription description = new EduCourseDescription();
                description.setDescription(courseInfoVo.getDescription());
                description.setId(courseInfoVo.getId());
                int rowDes = descriptionMapper.updateById(description);
                if(rowDes > 0){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }
    }
}
