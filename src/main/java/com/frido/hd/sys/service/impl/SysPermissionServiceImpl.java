package com.frido.hd.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.frido.hd.sys.entity.SysPermission;
import com.frido.hd.sys.mapper.SysPermissionMapper;
import com.frido.hd.sys.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frido.hd.sys.vo.resp.SysPermissionRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ldhao
 * @since 2020-10-23
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {
    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermissionRespVO> selectAll() {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted",1);
        List<SysPermission> sysPermissionList = sysPermissionMapper.selectList(wrapper);
        List<SysPermissionRespVO> result = new ArrayList<>();
        if(!sysPermissionList.isEmpty()){
            for (SysPermission sysPermission:sysPermissionList){
                SysPermissionRespVO sysPermissionRespVO = new SysPermissionRespVO();
                BeanUtils.copyProperties(sysPermissionRespVO,sysPermission);
                QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id",sysPermission.getPid());
                SysPermission parent = sysPermissionMapper.selectOne(queryWrapper);
                if(parent!=null){
                    sysPermissionRespVO.setPidName(parent.getName());
                }
                result.add(sysPermissionRespVO);
            }
        }
        SysPermissionRespVO sysPermissionRespVO = new SysPermissionRespVO();
        sysPermissionRespVO.setCode("0001");
        sysPermissionRespVO.setName("123");
        sysPermissionRespVO.setPidName("123456");
        result.add(sysPermissionRespVO);
        return result;
    }
}
