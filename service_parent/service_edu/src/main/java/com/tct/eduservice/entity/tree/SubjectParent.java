package com.tct.eduservice.entity.tree;

import lombok.Data;

import java.util.List;

/**
 * 课程树形结构的父节点
 */
@Data
public class SubjectParent {
    public String id;
    public String title;
    public List<SubjectChildren> subjectChildrenList;
}
