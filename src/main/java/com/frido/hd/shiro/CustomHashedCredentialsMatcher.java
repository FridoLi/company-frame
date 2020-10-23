package com.frido.hd.shiro;

import com.frido.hd.Jwt.JwtTokenUtil;
import com.frido.hd.constants.Constant;
import com.frido.hd.exception.BusinessException;
import com.frido.hd.exception.code.BaseResponseCode;
import com.frido.hd.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
@Slf4j
public class CustomHashedCredentialsMatcher extends HashedCredentialsMatcher {
    @Autowired
    private RedisService redisService;
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomPasswordToken customPasswordToken = (CustomPasswordToken) token;
        String accessToken = (String)customPasswordToken.getCredentials();
        String userId = JwtTokenUtil.getUserId(accessToken);
        log.info("doCredentialsMatch---userId={}",userId);
        //判断用户是否被删除
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        //判断用户是否被锁定
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        //校验token
        if(!JwtTokenUtil.validateToken(accessToken)){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        return true;
    }
}
