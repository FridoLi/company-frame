package com.frido.hd.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frido.hd.sys.entity.SysPermission;
import com.frido.hd.sys.vo.req.PermissionAddReqVO;
import com.frido.hd.sys.vo.resp.PermissionRespNode;
import com.frido.hd.sys.vo.resp.SysPermissionRespVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldhao
 * @since 2020-10-23
 */
public interface ISysPermissionService extends IService<SysPermission> {
    List<SysPermissionRespVO> selectAll();
    List<PermissionRespNode> selectAllMenuByTree();
    SysPermission addPermission(PermissionAddReqVO vo);
}
