package com.tct.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.tct.oss.service.IOssFileService;
import com.tct.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssFileServiceImpl implements IOssFileService {

    @Override
    public String upload(MultipartFile file) {
        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        try {
            // 创建oss实例
            OSS ossClient = new OSSClientBuilder().build(endPoint,accessKeyId,accessKeySecret);
            // 获取上传文件流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();
            //1 在文件名称添加一个随机值
            String uuid = UUID.randomUUID().toString().replace("-","");
            fileName = uuid + fileName;
            //2 使用日期生成文件夹路径
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = datePath + "/" + fileName;
            // 调用OSS方法实现上传
            ossClient.putObject(bucketName,fileName,inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // 获取上传文件的URL
            String url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
