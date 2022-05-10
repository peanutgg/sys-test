package com.sys.test.auth.service.impl;

import com.sys.test.auth.mapper.SysUserMapper;
import com.sys.test.auth.po.SysPermission;
import com.sys.test.auth.po.SysRole;
import com.sys.test.auth.po.SysUser;
import com.sys.test.auth.service.ISysPermissionService;
import com.sys.test.auth.service.ISysRoleService;
import com.sys.test.auth.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * <code>UserServiceImpl</code>
 * </p>
 * Description:
 *
 * @author jianzh5
 * @date 2020/7/23 16:21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserMapper sysUserMapper;
    private final ISysRoleService sysRoleService;
    private final ISysPermissionService sysPermissionService;

    @Override
    public SysUser getUserByMobile(String mobile) {
        SysUser sysUser = sysUserMapper.selectByMobile(mobile);
        if (sysUser != null) {
            //获取当前用户的所有角色
            List<SysRole> roleList = sysRoleService.listRolesByUserId(sysUser.getId());
            sysUser.setRoles(roleList.stream().map(SysRole::getRoleCode).collect(Collectors.toList()));
            List<Integer> roleIds = roleList.stream().map(SysRole::getId).collect(Collectors.toList());
            //获取所有角色的权限
            List<SysPermission> permissionList = sysPermissionService.listPermissionsByRoles(roleIds);

            sysUser.setPermissions(
                    permissionList.stream()
                            .map(SysPermission::getPermission)
                            .collect(Collectors.toList())
            );
        }
        return sysUser;
    }
}
