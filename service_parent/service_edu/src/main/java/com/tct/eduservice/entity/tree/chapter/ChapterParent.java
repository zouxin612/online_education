package com.tct.eduservice.entity.tree.chapter;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 章节父节点
 */
@Data
public class ChapterParent implements Serializable {
    public String id;
    public String title;
    public List<ChapterChildren> children;
}
