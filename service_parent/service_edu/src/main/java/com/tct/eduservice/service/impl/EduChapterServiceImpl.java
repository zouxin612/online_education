package com.tct.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tct.eduservice.entity.EduChapter;
import com.tct.eduservice.entity.EduVideo;
import com.tct.eduservice.entity.tree.chapter.ChapterChildren;
import com.tct.eduservice.entity.tree.chapter.ChapterParent;
import com.tct.eduservice.mapper.EduChapterMapper;
import com.tct.eduservice.mapper.EduVideoMapper;
import com.tct.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    // 注入章节mapper
    @Autowired
    public EduChapterMapper chapterMapper;

    // 注入小节maper
    @Autowired
    public EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterParent> getChapterInfoTree(String courseId) {
        // 创建数据返回对象
        List<ChapterParent> parentList = new ArrayList<>();

        // 根据课程id查询章节信息
        // 创建查询条件对象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = chapterMapper.selectList(wrapper);

        // 根据课程id查询小节现象
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> videoList = eduVideoMapper.selectList(wrapper1);

        if(null != chapterList && chapterList.size() > 0){
            // 循环遍历出该课程下的所有章节
            for (EduChapter chapter: chapterList) {
                // 创建章节父节点对象
                ChapterParent parent = new ChapterParent();
                BeanUtils.copyProperties(chapter,parent);
                // 封装完章节父节点信息，遍历循环小节信息
                //创建集合，用于封装章节的小节
                List<ChapterChildren> videos = new ArrayList<>();
                for (EduVideo video:videoList) {
                    // 判断该小节是否属于该章节
                    if(chapter.getId().equals(video.getChapterId())){
                        // 创建章节子节点对象
                        ChapterChildren children = new ChapterChildren();
                        BeanUtils.copyProperties(video,children);
                        videos.add(children);
                        // 将子节点数据添加到父节点
                        parent.setChildren(videos);
                    }
                }
                // 封装到返回对象数据
                parentList.add(parent);
            }
        }
        return parentList;
    }

    /**
     * 根据章节的id删除章节信息
     * 在此处若章节下有小节信息,则该章节不能删除
     * @param chapterId
     * @return
     */
    @Override
    public int deleteChapterInfoById(String chapterId) {
        // 根据章节id查询小节信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chapter_id",chapterId);
        Integer count = eduVideoMapper.selectCount(wrapper);
        if(count > 0){
            // 返回1表示有小节不能删除
            return 1;
        }else {
            // 执行删除操作
            int i = chapterMapper.deleteById(chapterId);
            if(i > 0){
                // 表示成功
                return 0;
            }else {
                // 表示失败
                return 2;
            }
        }
    }
}
