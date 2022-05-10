package com.sys.test.auth.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sys.test.auth.po.SysPermission;

import java.util.List;

/**
 * <p>
 * <code>ISysRoleService</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/8/5 9:44
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 获取所有角色的权限
     *
     * @param roleIds 角色id列表
     * @return List<SysPermission>
     * @author javadaily
     * @date 2020/8/5 12:46
     */
    List<SysPermission> listPermissionsByRoles(List<Integer> roleIds);
}
