package com.frido.hd.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frido.hd.sys.entity.SysUser;
import com.frido.hd.sys.vo.req.LoginReqVO;
import com.frido.hd.sys.vo.req.UserPageReqVO;
import com.frido.hd.sys.vo.resp.LoginRespVO;
import com.frido.hd.sys.vo.resp.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ldhao
 * @since 2020-10-13
 */
public interface ISysUserService extends IService<SysUser> {

    LoginRespVO login(LoginReqVO vo);
    PageVO<SysUser> pageInfo(UserPageReqVO vo);
}
