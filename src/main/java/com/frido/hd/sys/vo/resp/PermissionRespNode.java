package com.frido.hd.sys.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PermissionRespNode {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String title;
    @ApiModelProperty(value = "接口地址")
    private String url;
    private List<?> children;
}
