package com.sys.test.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sys.test.auth.mapper.SysPermissionMapper;
import com.sys.test.auth.po.SysPermission;
import com.sys.test.auth.service.ISysPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<SysPermission> listPermissionsByRoles(List<Integer> roleIds) {
        return baseMapper.listPermissionsByRoles(roleIds);
    }
}
