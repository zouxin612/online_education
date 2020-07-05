package com.tct.eduservice.service;

import com.tct.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tct.eduservice.entity.tree.chapter.ChapterParent;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterParent> getChapterInfoTree(String courseId);

    // 根据章节的id删除章节信息
    int deleteChapterInfoById(String chapterId);
}
