package com.tct.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.tct.eduservice.entity.EduSubject;
import com.tct.eduservice.excel.ExcelSubjectData;
import com.tct.eduservice.listener.SubjectExcelListener;
import com.tct.eduservice.mapper.EduSubjectMapper;
import com.tct.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
}
