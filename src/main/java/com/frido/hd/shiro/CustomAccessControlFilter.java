package com.frido.hd.shiro;

import com.alibaba.fastjson.JSON;
import com.frido.hd.constants.Constant;
import com.frido.hd.exception.BusinessException;
import com.frido.hd.exception.code.BaseResponseCode;
import com.frido.hd.utils.DataResult;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            Object o) throws Exception {
//        Subject subject = getSubject(servletRequest, servletResponse);
//        String[] rolesArray = (String[]) o;
//        //没有权限访问
//        if (rolesArray == null || rolesArray.length == 0) {
//            return true;
//        }
//        for (int i = 0; i < rolesArray.length; i++) {
//            //若当前用户是rolesArray中的任何一个，则有权限访问
//            if (subject.hasRole(rolesArray[i])) {
//                return true;
//            }
//        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(
            ServletRequest servletRequest,
            ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        log.info("request 接口地址，{}",request.getRequestURI());
        log.info("request 接口的请求方式，{}",request.getMethod());
        String accessToken = request.getHeader(Constant.ACCESS_TOKEN);
        try {
            if(StringUtils.isNullOrEmpty(accessToken)){
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            CustomPasswordToken customPasswordToken = new CustomPasswordToken(accessToken);
            getSubject(servletRequest,servletResponse).login(customPasswordToken);
        }catch (BusinessException e){
            customResponse(servletResponse,e.getMessageCode(),e.getDetailMessage());
            return false;
        } catch (AuthenticationException e){
            if(e.getCause() instanceof BusinessException) {
                BusinessException businessException = (BusinessException)e.getCause();
                customResponse(servletResponse, businessException.getMessageCode(),businessException.getDetailMessage());
            } else {
                customResponse(servletResponse,BaseResponseCode.TOKEN_ERROR.getCode(),BaseResponseCode.TOKEN_ERROR.getMsg());
            }
            return false;
        } catch (Exception e){
            customResponse(servletResponse,BaseResponseCode.SYSTEM_ERROR.getCode(), BaseResponseCode.SYSTEM_ERROR.getMsg());
            return false;
        }
        return true;
    }
    private void customResponse(ServletResponse response,int code,String msg)  {
        try{
            DataResult result = DataResult.getResult(code,msg);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setCharacterEncoding("UTF-8");
            String str = JSON.toJSONString(result);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(str.getBytes("UTF-8"));
            outputStream.flush();
        }catch (IOException e){
            log.error("customResponse---error:{}",e);
        }
    }
}
