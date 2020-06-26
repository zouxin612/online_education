package com.tct.oss.controller;

import com.tct.commonutils.ResponseResult;
import com.tct.oss.service.IOssFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "OSS文件管理")
@RestController
@CrossOrigin
@RequestMapping("/oss/file")
public class OssFileUploadController {

    @Autowired
    public IOssFileService ossFileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public ResponseResult fileUpload(@ApiParam(required = true,name = "文件")
                                     @RequestParam(name = "file") MultipartFile file){
       if(null != file){
           String filePath = ossFileService.upload(file);
           // 返回成功对象
           return ResponseResult.ok().message("文件上传成功").data("url",filePath);
       }else{
           // 返回成功对象
           return ResponseResult.error().message("文件上传失败");
       }
    }
}
