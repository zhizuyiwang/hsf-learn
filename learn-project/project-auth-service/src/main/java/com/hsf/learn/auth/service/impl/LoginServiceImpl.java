package com.hsf.learn.auth.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.hsf.learn.auth.service.ILoginService;
import com.hsf.learn.auth.service.ISysUserInfoService;
import com.hsf.learn.auth.shiro.PhoneToken;
import com.hsf.learn.common.redis.config.RedisUtil;
import com.hsf.learn.common.utils.DataUtils;
import com.hsf.learn.common.utils.auth.AuthUtils;
import com.hsf.learn.common.utils.enumset.LoginTypeEnum;
import com.hsf.learn.common.utils.exception.CoreServiceException;
import com.hsf.learn.common.utils.http.HttpClientResult;
import com.hsf.learn.common.utils.response.RespCode;
import com.hsf.learn.common.utils.sms.InternalYunpianSms;
import com.hsf.learn.core.model.common.dto.UserInfoDTO;
import com.hsf.learn.core.model.common.entity.UserInfoModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class LoginServiceImpl implements ILoginService {
    @Resource(name = "sysUserInfoServiceImpl")
    private ISysUserInfoService sysUserInfoServiceImpl;
    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;
    @Override
    public JSONObject userLogin(String userName, String password, String loginType) {
        AuthenticationToken token = null;
        if (StringUtils.equals(loginType, LoginTypeEnum.USERNAME.getCode())) {
            //用户名密码登录
            token = new UsernamePasswordToken(userName, password);
        } else if(StringUtils.equals(loginType, LoginTypeEnum.PHONE.getCode())){
            //手机验证码登录
            token = new PhoneToken(userName, password);
        } else {
            throw new CoreServiceException(RespCode.FAIL.getCode(),"不支持的登录方式");
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        UserInfoDTO userInfoDTO = (UserInfoDTO) subject.getPrincipal();

        String jwtToken = userInfoDTO.getToken();
        subject.getSession().setAttribute("user", userInfoDTO);
        JSONObject result = new JSONObject();
        result.put("token", jwtToken);
        result.put("userInfo", userInfoDTO);
        //TODO 是否演示账户
//        result.put("demo", "0");
        return result;
    }

    @Override
    public void userLogout(Long userId) {
        //删除redis
        if (DataUtils.isNullOrEmpty(userId)) {
            throw new CoreServiceException(RespCode.FAIL.getCode(), "退出失败");
        }
        UserInfoModel userModel = sysUserInfoServiceImpl.selectUserById(userId);
        if (DataUtils.isNullOrEmpty(userModel)) {
            throw new CoreServiceException(RespCode.FAIL.getCode(), "用户不存在");
        }
        String key = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_ID, userModel.getId());
        String keyLoginName = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_LOGIN_NAME,
                userModel.getLoginName() + "@" + userModel.getOrgCode());
        redisUtil.del(new String[] {key, keyLoginName});
    }

    @Override
    public void sendSmsCode(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            throw new CoreServiceException("手机号不能为空");
        }
        if (!AuthUtils.valiMobile(mobile)) {
            throw new CoreServiceException("请输入正确的手机号");
        }
        // 获取用户信息
        UserInfoModel userModel = sysUserInfoServiceImpl.selectUserByPhone(mobile);
        if (userModel == null) {
            throw new CoreServiceException("用户不存在");
        }
        String key = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_MOBILE_SMSCODE, mobile);
        String redisSmsCode = redisUtil.get(key);
        if (StringUtils.isNotBlank(redisSmsCode)) {
            throw new CoreServiceException("验证码已经发送，请注意查收");
        }
        String smsCode  = ((int)(Math.random()*9 + 1) * 100000) + "";
        Runnable runnable = () ->{
            //发送短信
            HttpClientResult httpResult;
            try {
                String smsText = "【倍肯机电】您的验证码是" + smsCode;
                httpResult = InternalYunpianSms.sendSms(smsText, mobile);
                //发送成功
                if(httpResult.getCode() == 200){
                    redisUtil.setex(key, smsCode, AuthUtils.SMS_CODE_EXPTIME);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

}
