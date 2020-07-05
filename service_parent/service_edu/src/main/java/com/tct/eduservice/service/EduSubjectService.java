package com.tct.eduservice.service;

import com.tct.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tct.eduservice.entity.tree.subject.SubjectParent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author tct
 * @since 2020-06-27
 */
public interface EduSubjectService extends IService<EduSubject> {

    // excel数据导入
    public boolean saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    // 树形数据获取
    public List<SubjectParent> getSubjectNodeList();
}
