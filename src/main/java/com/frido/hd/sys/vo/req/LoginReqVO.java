package com.frido.hd.sys.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReqVO {
    @ApiModelProperty(value = "账号")
    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "登录类型（1:pc;2:app）")
    @NotBlank(message = "登录类型不能为空")
    private String type;
}
