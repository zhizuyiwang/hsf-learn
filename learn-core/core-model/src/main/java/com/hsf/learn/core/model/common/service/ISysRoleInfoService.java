package com.hsf.learn.core.model.common.service;

/**
 *用户角色权限服务
 */
public interface ISysRoleInfoService {
    boolean hasPermissions(String contextPath, String uri, String method, Long user_id);
}
