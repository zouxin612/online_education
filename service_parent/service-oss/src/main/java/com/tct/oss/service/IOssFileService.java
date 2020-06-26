package com.tct.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface IOssFileService {

    /**
     * 文件上传至OSS服务器
     */
    String upload(MultipartFile file);
}
