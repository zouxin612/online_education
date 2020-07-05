package com.tct.eduservice.entity.tree.chapter;

import lombok.Data;

import java.io.Serializable;

/**
 * 章节子节点
 */
@Data
public class ChapterChildren implements Serializable {
    public String id;
    public String title;
}
