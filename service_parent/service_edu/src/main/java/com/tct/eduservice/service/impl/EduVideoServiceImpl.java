package com.tct.eduservice.service.impl;

import com.tct.eduservice.entity.EduVideo;
import com.tct.eduservice.entity.vo.EduVideoInfoVo;
import com.tct.eduservice.mapper.EduVideoMapper;
import com.tct.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean saveVideoInfo(EduVideoInfoVo eduVideoInfoVo) {
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(eduVideoInfoVo, video);
        boolean result = this.save(video);

        if(!result){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public boolean updateVideoInfo(EduVideoInfoVo eduVideoInfoVo) {
        //保存课时基本信息
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(eduVideoInfoVo, video);
        boolean result = this.updateById(video);
        if(!result){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public EduVideoInfoVo getVideoInfoFormById(String id) {
        //从video表中取数据
        EduVideo video = this.getById(id);
        if(video == null){
            return null;
        }

        //创建videoInfoForm对象
        EduVideoInfoVo videoInfoForm = new EduVideoInfoVo();
        BeanUtils.copyProperties(video, videoInfoForm);

        return videoInfoForm;
    }
}
