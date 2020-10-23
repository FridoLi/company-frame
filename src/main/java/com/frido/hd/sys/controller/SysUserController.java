package com.frido.hd.sys.controller;


import com.frido.hd.sys.entity.SysUser;
import com.frido.hd.sys.service.ISysUserService;
import com.frido.hd.sys.vo.req.LoginReqVO;
import com.frido.hd.sys.vo.req.UserPageReqVO;
import com.frido.hd.sys.vo.resp.LoginRespVO;
import com.frido.hd.sys.vo.resp.PageVO;
import com.frido.hd.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ldhao
 * @since 2020-10-13
 */
@RestController
@Api( tags = "组织模块-用户管理")
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private ISysUserService iSysUserService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> testResult(@RequestBody @Valid LoginReqVO vo){
        DataResult<LoginRespVO> result = DataResult.success();
        result.setData(iSysUserService.login(vo));
        return result;
    }
    @PostMapping("/users")
    @ApiOperation(value = "分页获取用户列表接口")
    @RequiresPermissions(value = "sys:user:list")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo){
        DataResult<PageVO<SysUser>> result=DataResult.success();
        result.setData(iSysUserService.pageInfo(vo));
        return result;
    }

 
}

