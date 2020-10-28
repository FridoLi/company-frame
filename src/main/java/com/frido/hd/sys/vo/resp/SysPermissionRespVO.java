package com.frido.hd.sys.vo.resp;

import com.frido.hd.sys.entity.SysPermission;
import lombok.Data;

@Data
public class SysPermissionRespVO extends SysPermission {
    private String pidName;
}
