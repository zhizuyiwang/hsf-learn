package com.hsf.learn.auth.shiro;

import com.alibaba.fastjson.JSONObject;
import com.hsf.learn.common.redis.config.RedisUtil;
import com.hsf.learn.common.utils.Jwt.JwtUtils;
import com.hsf.learn.common.utils.auth.AuthUtils;
import com.hsf.learn.common.utils.exception.CoreServiceException;
import com.hsf.learn.common.utils.response.RespCode;
import com.hsf.learn.core.model.common.dto.UserInfoDTO;
import com.hsf.learn.core.model.common.entity.UserInfoModel;
import com.hsf.learn.core.model.common.service.ISysUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

public class PhoneRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(PhoneRealm.class);

	@Resource(name = "sysUserInfoServiceImpl")
	private ISysUserInfoService sysUserInfoServiceImpl;
	@Resource(name = "redisUtil")
	private RedisUtil redisUtil;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		PhoneToken phoneToken = null;
		// 如果是PhoneToken，则强转，获取phone；否则不处理。
		if (authenticationToken instanceof PhoneToken) {
			phoneToken = (PhoneToken) authenticationToken;
		} else {
			return null;
		}

		String mobile = phoneToken.getPhone();
		if (StringUtils.isBlank(mobile)) {
			throw new CodecException("手机号不能为空");
		}
		if (!AuthUtils.valiMobile(mobile)) {
			throw new CodecException("请输入正确的手机号");
		}
		// 获取用户信息
		UserInfoModel userModel = sysUserInfoServiceImpl.selectUserByPhone(mobile);
		if (userModel == null) {
			throw new CodecException("用户不存在");
		}
		// 验证短信是否正确
		String key = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_MOBILE_SMSCODE, mobile);
		String redisSmsCode = redisUtil.get(key);
		if (!StringUtils.equals("6666", phoneToken.getSmsCode())) {//通用验证码6666
			if (StringUtils.isBlank(redisSmsCode) 
					|| !StringUtils.equals(redisSmsCode, phoneToken.getSmsCode())) {
				throw new CodecException("验证码错误");
			}
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

            UserInfoDTO userInfoDTO = new UserInfoDTO();
            BeanUtils.copyProperties(userModel, userInfoDTO);
			userInfoDTO.setToken(jwtToken);
            return new SimpleAuthenticationInfo(userInfoDTO, jwtToken, this.getName());
        } catch (UnsupportedEncodingException | JoseException e) {
            logger.info("登录失败,userPhone={}", phoneToken.getPhone());
            throw new CoreServiceException(RespCode.FAIL.getCode(), "登录失败");
        }
	}
	
	//重写凭证验证方法
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
            throws AuthenticationException {
    }
	
	@Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof PhoneToken;
    }
}
