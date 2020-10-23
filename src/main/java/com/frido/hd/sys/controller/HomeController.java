package com.frido.hd.sys.controller;

import com.frido.hd.Jwt.JwtTokenUtil;
import com.frido.hd.constants.Constant;
import com.frido.hd.sys.service.IHomeService;
import com.frido.hd.sys.vo.resp.HomeRespVO;
import com.frido.hd.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@Api(tags = "首页数据")
public class HomeController {
    @Resource
    private IHomeService iHomeService;

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest request){
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(accessToken);
        DataResult<HomeRespVO> result = DataResult.success();
        result.setData(iHomeService.getHomeInfo(userId));
        return result;
    }
}
