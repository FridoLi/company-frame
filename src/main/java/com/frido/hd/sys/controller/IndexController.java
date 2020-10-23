package com.frido.hd.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "视图",description = "负责返回视图")
@Controller
@RequestMapping("index")
public class IndexController {
    @GetMapping("/404")
    @ApiOperation(value = "跳转404错误界面")
    public String error404(){
        return "error/404";
    }
    @GetMapping("/login")
    @ApiOperation(value = "跳转登录界面")
    public String logout(){
        return "login";
    }
    @GetMapping("/home")
    @ApiOperation(value = "跳转首页界面")
    public String home(){
        return "home";
    }
    @GetMapping("/main")
    @ApiOperation(value = "跳转默认界面")
    public String main(){
        return "main";
    }
}
