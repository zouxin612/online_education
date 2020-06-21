package com.tct.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tct.eduservice.entity.EduTeacher;
import com.tct.eduservice.entity.vo.TeacherQueryVo;
import com.tct.eduservice.mapper.EduTeacherMapper;
import com.tct.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author tct
 * @since 2020-05-05
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    public EduTeacherMapper eduTeacherMapper;

    /**
     * 条件分页查询
     * @param pageParam
     * @param teacherQuery
     */
    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQueryVo teacherQuery) {
        //创建条件查询对象
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        if (teacherQuery == null){
            eduTeacherMapper.selectPage(pageParam, queryWrapper);
            return ;
        }
        // 获取条件查询数据
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        queryWrapper.orderByDesc("gmt_create");

        // 条件查询
        eduTeacherMapper.selectPage(pageParam, queryWrapper);
    }
}
