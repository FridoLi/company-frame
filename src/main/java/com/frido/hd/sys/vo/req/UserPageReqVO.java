package com.frido.hd.sys.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPageReqVO {
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum = 1;
    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize = 10;
}
