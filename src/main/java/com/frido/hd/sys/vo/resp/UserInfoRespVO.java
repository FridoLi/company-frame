package com.frido.hd.sys.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfoRespVO {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String id;

    /**
     * 账户名称
     */
    @ApiModelProperty(value = "账号")
    private String username;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private String deptId;

    /**
     * 真实名称
     */
    @ApiModelProperty(value = "真实名称")
    private String realName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

}
