package com.frido.hd.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.frido.hd.sys.entity.SysUser;
import com.frido.hd.sys.mapper.SysUserMapper;
import com.frido.hd.sys.service.IHomeService;
import com.frido.hd.sys.vo.resp.HomeRespVO;
import com.frido.hd.sys.vo.resp.PermissionRespNode;
import com.frido.hd.sys.vo.resp.UserInfoRespVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class HomeServiceImpl implements IHomeService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public HomeRespVO getHomeInfo(String userId) {
        HomeRespVO homeRespVO = new HomeRespVO();
        String home="[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"id\":\"6\",\"title\":\"五级类目5-6\",\"url\":\"string\"}],\"id\":\"5\",\"title\":\"四级类目4- 5\",\"url\":\"string\"}],\"id\":\"4\",\"title\":\"三级类目3- 4\",\"url\":\"string\"}],\"id\":\"3\",\"title\":\"二级类目2- 3\",\"url\":\"string\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"string\"},{\"children\": [],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"string\"}]";
        List<PermissionRespNode> list = JSON.parseArray(home,PermissionRespNode.class);
        homeRespVO.setMenus(list);
        SysUser sysUser =sysUserMapper.selectById(userId);
        UserInfoRespVO respVO = new UserInfoRespVO();
        if(sysUser!=null){
            respVO.setUsername(sysUser.getUsername());
            respVO.setDeptName("豪宅");
            respVO.setId(sysUser.getId());
        }
        homeRespVO.setUserInfoRespVO(respVO);
        return homeRespVO;
    }
}
