package com.frido.hd.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frido.hd.exception.BusinessException;
import com.frido.hd.exception.code.BaseResponseCode;
import com.frido.hd.sys.entity.SysPermission;
import com.frido.hd.sys.mapper.SysPermissionMapper;
import com.frido.hd.sys.service.ISysPermissionService;
import com.frido.hd.sys.vo.req.PermissionAddReqVO;
import com.frido.hd.sys.vo.resp.PermissionRespNode;
import com.frido.hd.sys.vo.resp.SysPermissionRespVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        return result;
    }

    @Override
    public List<PermissionRespNode> selectAllMenuByTree() {
        List<SysPermissionRespVO> list=selectAll();
        List<PermissionRespNode> result=new ArrayList<>();
        //新增顶级目录是为了方便添加一级目录
        PermissionRespNode respNode=new PermissionRespNode();
        respNode.setId("0");
        respNode.setTitle("默认顶级菜单");
        respNode.setSpread(true);
        respNode.setChildren(getTree(list));
        result.add(respNode);
        return result;
    }

    @Override
    public SysPermission addPermission(PermissionAddReqVO vo) {
        SysPermission sysPermission=new SysPermission();
        BeanUtils.copyProperties(vo,sysPermission);
        verifyForm(sysPermission);
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermission.setCreateTime(new Date());
        int count=sysPermissionMapper.insert(sysPermission);
        if (count!=1){
             throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysPermission;
    }

    private void verifyForm(SysPermission sysPermission){

        SysPermission parent=sysPermissionMapper.selectById(sysPermission.getPid());
        switch (sysPermission.getType()){
            case 1:
                if(parent!=null){
                    if(parent.getType()!=1){
                        throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                    }
                }else if (!sysPermission.getPid().equals("0")){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_CATALOG_ERROR);
                }
                break;
            case 2:
                if(parent==null||parent.getType()!=1){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_MENU_ERROR);
                }
                if(org.springframework.util.StringUtils.isEmpty(sysPermission.getUrl())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                break;
            case 3:
                if(parent==null||parent.getType()!=2){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_BTN_ERROR);
                }
                if(org.springframework.util.StringUtils.isEmpty(sysPermission.getPerms())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_PERMS_NULL);
                }
                if(org.springframework.util.StringUtils.isEmpty(sysPermission.getUrl())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_NOT_NULL);
                }
                if(org.springframework.util.StringUtils.isEmpty(sysPermission.getMethod())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_METHOD_NULL);
                }
                if(StringUtils.isEmpty(sysPermission.getCode())){
                    throw new BusinessException(BaseResponseCode.OPERATION_MENU_PERMISSION_URL_CODE_NULL);
                }
                break;
        }
    }

    private List<PermissionRespNode> getTree(List<SysPermissionRespVO> all){
        List<PermissionRespNode> list=new ArrayList<>();
        if (all==null||all.isEmpty()){
          return list;
        }
        for(SysPermission sysPermission:all){
          if(sysPermission.getPid().equals("0")){
            PermissionRespNode permissionRespNode=new PermissionRespNode();
            BeanUtils.copyProperties(sysPermission,permissionRespNode);
            permissionRespNode.setTitle(sysPermission.getName());
            permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(),all));
            list.add(permissionRespNode);
          }
        }
        return list;
    }
    private List<PermissionRespNode>getChildExcBtn(String id,List<SysPermissionRespVO> all){
        List<PermissionRespNode> list=new ArrayList<>();
        for(SysPermission sysPermission:all){
          if(sysPermission.getPid().equals(id)&&sysPermission.getType()!=3){
            PermissionRespNode permissionRespNode=new PermissionRespNode();
            BeanUtils.copyProperties(sysPermission,permissionRespNode);
            permissionRespNode.setTitle(sysPermission.getName());
            permissionRespNode.setChildren(getChildExcBtn(sysPermission.getId(),all));
            list.add(permissionRespNode);
          }
        }
        return list;
    }
}
