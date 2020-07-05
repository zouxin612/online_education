package com.tct.eduservice.service;

import com.tct.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tct.eduservice.entity.vo.EduVideoInfoVo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author tct
 * @since 2020-07-02
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean saveVideoInfo(EduVideoInfoVo eduVideoInfoVo);

    boolean updateVideoInfo(EduVideoInfoVo eduVideoInfoVo);

    EduVideoInfoVo getVideoInfoFormById(String id);

}
