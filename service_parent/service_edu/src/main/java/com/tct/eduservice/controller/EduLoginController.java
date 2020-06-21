package com.tct.eduservice.controller;

import com.tct.commonutils.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户相关操作")
@RestController
@RequestMapping("/edu/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    @ApiOperation("用户登陆操作")
    @PostMapping("/login")
    public ResponseResult userLogin(){
        return ResponseResult.ok().data("token","admin");
    }

    @ApiOperation("用户信息")
    @GetMapping("/info")
    public ResponseResult getUserInfo(){
        return ResponseResult.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
