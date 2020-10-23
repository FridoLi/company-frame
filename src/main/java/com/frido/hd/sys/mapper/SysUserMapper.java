package com.frido.hd.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.frido.hd.sys.entity.SysUser;
import com.frido.hd.sys.vo.req.UserPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ldhao
 * @since 2020-10-13
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getUserInfoByName(String username);
    List<SysUser> selectAll(UserPageReqVO vo);
}
