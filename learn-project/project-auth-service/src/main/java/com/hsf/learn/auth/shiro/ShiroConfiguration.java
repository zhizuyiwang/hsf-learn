package com.hsf.learn.auth.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.net.httpserver.AuthFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ShiroConfiguration {

	// 将自己的验证方式加入容器
	@Bean
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		return userRealm;
	}

	@Bean
	public PhoneRealm phoneRealm() {
		PhoneRealm phoneRealm = new PhoneRealm();
		return phoneRealm;
	}

	// 授权及认证管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager() {
		List<Realm> realmList = new ArrayList<>();
		realmList.add(userRealm());
		realmList.add(phoneRealm());
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealms(realmList);

		// TODO 自定义缓存实现，可以使用redis
//        securityManager.setCacheManager(shiroCacheManager());
		// TODO 自定义session管理，可以使用redis
//        securityManager.setSessionManager(sessionManager());
		// TODO 注入记住我管理器
//        securityManager.setRememberMeManager(rememberMeManager());
		// TODO 认证器
//        securityManager.setAuthenticator(abstractAuthenticator);

		return securityManager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

}
