package com.frido.hd.shiro;

import com.frido.hd.Jwt.JwtTokenUtil;
import com.frido.hd.constants.Constant;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Collection;

public class CustomRealm extends AuthorizingRealm {
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomPasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String accessToken = (String) principalCollection.getPrimaryPrincipal();
        Claims claimsFromToken =  JwtTokenUtil.getClaimsFromToken(accessToken);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(claimsFromToken.get(Constant.ROLES_INFOS_KEY)!=null){
            info.addRoles((Collection<String>)claimsFromToken.get(Constant.ROLES_INFOS_KEY));
        }
        if(claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY)!=null){
            info.addStringPermissions((Collection<String>)claimsFromToken.get(Constant.PERMISSIONS_INFOS_KEY));
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomPasswordToken customPasswordToken = (CustomPasswordToken) authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(customPasswordToken.getPrincipal(),customPasswordToken.getCredentials(),CustomRealm.class.getName());
        return info;
    }
}
