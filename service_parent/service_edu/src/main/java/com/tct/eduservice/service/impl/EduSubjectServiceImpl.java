package com.tct.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tct.eduservice.entity.EduSubject;
import com.tct.eduservice.entity.tree.SubjectChildren;
import com.tct.eduservice.entity.tree.SubjectParent;
import com.tct.eduservice.excel.ExcelSubjectData;
import com.tct.eduservice.listener.SubjectExcelListener;
import com.tct.eduservice.mapper.EduSubjectMapper;
import com.tct.eduservice.mapper.EduTeacherMapper;
import com.tct.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author tct
 * @since 2020-06-27
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    
    @Autowired
    public EduSubjectMapper subjectMapper;
    
    @Override
    public boolean saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        // 判断文件是否为空
        if(file == null){
            return false;
        }
        // 根据文件读取的内容数据保存到数据库中
        // 获取文件输入流
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<SubjectParent> getSubjectNodeList() {
        // 创建数据返回对象
        List<SubjectParent> subjectParents = new ArrayList<>();

        // 获取父节点数据
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id","0");
        wrapper.orderByAsc("sort","id"); // 按照sort和id两个字段排序
        List<EduSubject> subjects = subjectMapper.selectList(wrapper);
        if(null == subjects || subjects.size()<0){
            return null;
        }

        //获取二级分类数据记录
        QueryWrapper queryWrapper2 = new QueryWrapper();
        queryWrapper2.ne("parent_id", 0);// 查找不等于父id为0的
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = subjectMapper.selectList(queryWrapper2);

        // 将数据填充到页面使用的实体中
        int subjectsSize = subjects.size(); // 获取父节点数据的个数
        for(int i = 0; i < subjectsSize;i++){
            // 获取父节点数据对象
            EduSubject subject = subjects.get(i);

            // 创建父节点对象
            SubjectParent subjectParent = new SubjectParent();
            // copy相同的属性值
            BeanUtils.copyProperties(subject,subjectParent);

            // 填充二级分类数据
            ArrayList<SubjectChildren> subjectVoArrayList = new ArrayList<>();
            for(int j = 0; j < subSubjects.size();j++){
                EduSubject subjectTow = subSubjects.get(j);
                // 比较二级的partentId与父级节点的id比较
                if(subject.getId().equals(subjectTow.getParentId())){
                    // 创建子节点对象
                    SubjectChildren subjectChildren = new SubjectChildren();
                    BeanUtils.copyProperties(subjectTow,subjectChildren);
                    subjectVoArrayList.add(subjectChildren);
                }
            }
            // 将二级节点的数据放到父节点对象中
            subjectParent.setSubjectChildrenList(subjectVoArrayList);
            // 在把数据放到集合中去
            subjectParents.add(subjectParent);
        }

        return subjectParents;
    }
}
