package com.frido.hd.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frido.hd.Jwt.JwtTokenUtil;
import com.frido.hd.constants.Constant;
import com.frido.hd.exception.BusinessException;
import com.frido.hd.exception.code.BaseResponseCode;
import com.frido.hd.sys.entity.SysUser;
import com.frido.hd.sys.mapper.SysUserMapper;
import com.frido.hd.sys.service.ISysUserService;
import com.frido.hd.sys.vo.req.LoginReqVO;
import com.frido.hd.sys.vo.req.UserPageReqVO;
import com.frido.hd.sys.vo.resp.LoginRespVO;
import com.frido.hd.sys.vo.resp.PageVO;
import com.frido.hd.utils.PageUtil;
import com.frido.hd.utils.PasswordUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldhao
 * @since 2020-10-13
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public LoginRespVO login(LoginReqVO vo) {
        SysUser sysUser = sysUserMapper.getUserInfoByName(vo.getUsername());
        if(null==sysUser){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(!sysUser.getStatus()){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_TIP);
        }
        if(!PasswordUtils.matches(sysUser.getSalt(),vo.getPassword(),sysUser.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO respVO = new LoginRespVO();
        BeanUtils.copyProperties(sysUser,respVO);
        Map<String,Object> claims = new HashMap<>();
        claims.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionsByUserId(sysUser.getId()));
        claims.put(Constant.ROLES_INFOS_KEY,getRolesByUserId(sysUser.getId()));
        claims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        String access_token = JwtTokenUtil.getAccessToken(sysUser.getId(),claims);
        String refresh_token;
        Map<String,Object> refreshTokenClaims = new HashMap<>();
        refreshTokenClaims.put(Constant.JWT_USER_NAME,sysUser.getUsername());
        if(vo.getType().equals("1")){
            refresh_token = JwtTokenUtil.getRefreshToken(sysUser.getId(),refreshTokenClaims);
        } else {
            refresh_token = JwtTokenUtil.getRefreshAppToken(sysUser.getId(),refreshTokenClaims);
        }
        respVO.setAccessToken(access_token);
        respVO.setRefreshToken(refresh_token);
        return respVO;
    }

    @Override
    public PageVO<SysUser> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.selectAll(vo);
        return PageUtil.getPageVO(sysUsers);
 
    }

    private List<String> getRolesByUserId(String userId){
        List<String> list = new ArrayList<>();
        if("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("admin");
        }else {
            list.add("test");
        }
        return list;
    }
    private List<String> getPermissionsByUserId(String userId){
        List<String> list = new ArrayList<>();
        if("9a26f5f1-cbd2-473d-82db-1d6dcf4598f8".equals(userId)){
            list.add("sys:user:add");
            list.add("sys:user:list");
            list.add("sys:user:update");
            list.add("sys:user:detail");
        }else {
            list.add("sys:user:add");
        }
        return list;
    }
}
