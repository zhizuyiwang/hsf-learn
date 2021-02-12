package com.hsf.learn.common.auth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hsf.learn.common.redis.config.RedisUtil;
import com.hsf.learn.common.utils.Jwt.JwtUtils;
import com.hsf.learn.common.utils.auth.AuthUtils;
import com.hsf.learn.common.utils.response.RespCode;
import com.hsf.learn.core.model.common.service.ISysRoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("authFilter")
public class AuthFilter extends BaseFilter implements Filter {

	@Resource(name = "redisUtil")
	private RedisUtil redisUtil;

//	@Reference
//	private ISysRoleInfoService sysRoleInfoService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//设置response使用utf-8码表，以控制response以什么码表向浏览器写出数据
		resp.setCharacterEncoding("UTF-8");
		System.out.println("拦截到");
		//指定浏览器以什么码表打开服务器发送的数据
		resp.setContentType("text/html;charset=utf-8");
		/**
		 * 获取servlet 的输出路径
		 */
		ServletOutputStream out = response.getOutputStream();
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		try {
//			String contextPath = req.getContextPath();
//			String uri = req.getRequestURI();
//			String method = req.getMethod();
//			log.info("contextPath:{},uri:{},method:{}", contextPath, uri, method);
//			if (isFreeAccess(contextPath, uri)) {
//				log.info("{} {} is free access", contextPath, uri);
//			} else {
//				// 获得token
//				String token = req.getHeader("token");
//				if (StringUtils.isNotBlank(token)) {
//					// 解密
//					String json = JwtUtils.parserTokenHS256(AuthUtils.SECURITY_KEY, token);
//					JSONObject jsonObj = JSONObject.parseObject(json);
//					if (jsonObj != null) {
//						request.setAttribute("user_id", jsonObj.getLong("user_id"));
//						request.setAttribute("user_code", jsonObj.getString("user_code"));
//						request.setAttribute("org_code", jsonObj.getString("org_code"));
//						request.setAttribute("demo", jsonObj.getString("demo"));
//					}
//					// 验证token
//					String key = String.format(AuthUtils.REDIS_KEY_MGR_SYS_USER_ID, jsonObj.getLong("user_id"));
//					String redisToken = redisUtil.get(key);
//					if (StringUtils.isBlank(redisToken)) {
//						resultMap.put("code", RespCode.FAIL.getCode());
//						resultMap.put("msg", "token无效");
//						resultMap.put("data", null);
//						out.write(JSONObject.toJSONString(resultMap).getBytes("utf-8"));
//						out.flush();
//						out.close();
//						return;
//					} else {
//						// 比较token是否相同
//						if (StringUtils.equals(redisToken, token)) {
//							// 不做操作
//						} else {
//							resultMap.put("code", RespCode.FAIL.getCode());
//							resultMap.put("msg", "token无效");
//							resultMap.put("data", null);
//							out.write(JSONObject.toJSONString(resultMap).getBytes("utf-8"));
//							out.flush();
//							out.close();
//							return;
//						}
//					}
//					if (isOtherFreeAccess(contextPath, uri)) {
//						//不做操作
//					} else {
////						// 用户角色与资源验证
////						if (!sysRoleInfoService.hasPermissions(contextPath, uri, method, jsonObj.getLong("user_id"))) {
////							resultMap.put("code", RespCode.FAIL.getCode());
////							resultMap.put("msg", "权限不足");
////							resultMap.put("data", null);
////							out.write(JSONObject.toJSONString(resultMap).getBytes("utf-8"));
////							out.flush();
////							out.close();
////							return;
////						}
//					}
//				} else {
//					resultMap.put("code", RespCode.FAIL.getCode());
//					resultMap.put("msg", "token无效");
//					resultMap.put("data", null);
//					out.write(JSONObject.toJSONString(resultMap).getBytes("utf-8"));
//					out.flush();
//					out.close();
//					return;
//				}
//			}
//		} catch (Exception e) {
//			log.error(e.getMessage(), e);
//			e.printStackTrace();
//			resultMap.put("code", RespCode.FAIL.getCode());
//			resultMap.put("msg", "服务暂时不可用");
//			resultMap.put("data", null);
//			out.write(JSONObject.toJSONString(resultMap).getBytes("utf-8"));
//			out.flush();
//			out.close();
//			return;
//		}
		chain.doFilter(request, response);
		System.out.println("servlet产生的响应被拦截到");
	}

}
