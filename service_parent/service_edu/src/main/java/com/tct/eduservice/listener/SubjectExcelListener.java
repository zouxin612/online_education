package com.tct.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tct.eduservice.entity.EduSubject;
import com.tct.eduservice.excel.ExcelSubjectData;
import com.tct.eduservice.service.EduSubjectService;

import java.util.Map;

/**
 * excel 解析监听类
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    // 由于SubjectExcelListener的使用是自己去new 不归spring容易管理，所以使用service需要使用构造方法注入
    public EduSubjectService subjectService;
    public SubjectExcelListener() {
    }
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if(excelSubjectData == null){
            return;
        }

        // 判断一级分类导入的数据是否存在
        EduSubject eduSubjectOne = exitSubject(subjectService,excelSubjectData.getOneSubjectName(),"0");
        if(eduSubjectOne == null){
            eduSubjectOne = new EduSubject();
            eduSubjectOne.setTitle(excelSubjectData.getOneSubjectName());
            eduSubjectOne.setParentId("0");
            subjectService.save(eduSubjectOne);
        }

        // 获取有效的以及parent_id
        String pid = eduSubjectOne.getId();
        // 判断二级分类导入的数据是否存在
        EduSubject eduSubjectTow = exitSubject(subjectService,excelSubjectData.getTwoSubjectName(),pid);
        if(eduSubjectTow == null){
            eduSubjectTow = new EduSubject();
            eduSubjectTow.setParentId(pid);
            eduSubjectTow.setTitle(excelSubjectData.getTwoSubjectName());
            subjectService.save(eduSubjectTow);
        }

    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}

    // 判断分类是否重复
    public EduSubject exitSubject(EduSubjectService subjectService,String name,String pid){
        // 创建查询数据条件对象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject subject = subjectService.getOne(wrapper);
        return subject;

    }
}
