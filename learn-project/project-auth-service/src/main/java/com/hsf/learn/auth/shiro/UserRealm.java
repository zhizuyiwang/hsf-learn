package com.hsf.learn.auth.shiro;

import com.alibaba.fastjson.JSONObject;
import com.hsf.learn.common.redis.config.RedisUtil;
import com.hsf.learn.common.utils.Jwt.JwtUtils;
import com.hsf.learn.common.utils.MD5Utils;
import com.hsf.learn.common.utils.auth.AuthUtils;
import com.hsf.learn.common.utils.exception.CoreServiceException;
import com.hsf.learn.common.utils.response.RespCode;
import com.hsf.learn.core.model.common.dto.UserInfoDTO;
import com.hsf.learn.core.model.common.entity.UserInfoModel;
import com.hsf.learn.core.model.common.service.ISysUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Resource(name = "sysUserInfoServiceImpl")
    private ISysUserInfoService sysUserInfoServiceImpl;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    /*
     * 认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /*
     * 授权
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = null;
        // 如果是PhoneToken，则强转，获取phone；否则不处理。
        if (authenticationToken instanceof UsernamePasswordToken) {
            token = (UsernamePasswordToken) authenticationToken;
        } else {
            return null;
        }
        // 获取用户信息
        UserInfoModel userModel = sysUserInfoServiceImpl.selectUserByLoginName(token.getUsername());
        // 账号不存在
        if (userModel == null) {
            logger.info("账号或密码不正确,username={}", token.getUsername());
            throw new CoreServiceException(RespCode.FAIL.getCode(), "账号或密码不正确");
        }
        //验证密码
        String md5Pwd = MD5Utils.MD5_32(userModel.getLoginName() + String.valueOf(token.getPassword()));
        if (!StringUtils.equals(userModel.getLoginPwd(), md5Pwd)) {
            logger.info("账号或密码不正确,username={}", token.getUsername());
            throw new CoreServiceException(RespCode.FAIL.getCode(), "账号或密码不正确");
        }
        // 生成用户token
        JSONObject userObj = new JSONObject();
        userObj.put("user_id", userModel.getId());
        userObj.put("org_code", userModel.getOrgCode());
        userObj.put("user_code", userModel.getCode());
        //TODO 是否演示账户
//		userObj.put("demo", userModel.getOrgCode());
        try {
            String jwtToken = JwtUtils.getTokenHS256(AuthUtils.SECURITY_KEY, userObj.toJSONString());
            String key_login_name = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_LOGIN_NAME,
                    userModel.getLoginName() + "@" + userModel.getOrgCode());
            String key_user_id = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_ID, userModel.getId());
            // token保存到redis
            redisUtil.setex(key_login_name, AuthUtils.TOKEN_EXPTIME, jwtToken);
            redisUtil.setex(key_user_id, AuthUtils.TOKEN_EXPTIME, jwtToken);

            UserInfoDTO userInfo = new UserInfoDTO();
            BeanUtils.copyProperties(userModel, userInfo);
            userInfo.setToken(jwtToken);
            return new SimpleAuthenticationInfo(userInfo, jwtToken, this.getName());
        } catch (UnsupportedEncodingException | JoseException e) {
            logger.info("登录失败,username={}", token.getUsername());
            throw new CoreServiceException(RespCode.FAIL.getCode(), "登录失败");
        }
    }

    //重写凭证验证方法
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
            throws AuthenticationException {
    }

    @Override
    public boolean supports(AuthenticationToken var1) {
        return var1 instanceof UsernamePasswordToken;
    }
}
