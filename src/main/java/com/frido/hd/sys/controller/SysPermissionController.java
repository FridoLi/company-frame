package com.frido.hd.sys.controller;


import com.frido.hd.sys.service.ISysPermissionService;
import com.frido.hd.sys.vo.resp.PermissionRespNode;
import com.frido.hd.sys.vo.resp.SysPermissionRespVO;
import com.frido.hd.utils.DataResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ldhao
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/sysPermission")
public class SysPermissionController {
    @Resource
    private ISysPermissionService iSysPermissionService;
    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有菜单权限接口")
    public DataResult<List<SysPermissionRespVO>> getAllMenuPermission(){
        DataResult<List<SysPermissionRespVO>> result = DataResult.success();
        result.setData(iSysPermissionService.selectAll());
        return result;
    }
    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口-查到目录")
    public DataResult<List<PermissionRespNode>> getAllMenusPermissionTree(){
        DataResult<List<PermissionRespNode>> result=DataResult.success();
        result.setData(iSysPermissionService.selectAllMenuByTree());
        return result;
    }
}

