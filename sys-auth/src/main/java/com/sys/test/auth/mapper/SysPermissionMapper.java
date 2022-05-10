package com.sys.test.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.test.auth.po.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product Dao层
 *
 * @author JAVA日知录
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 查找角色对应的资源
     *
     * @param roleIds 角色ids
     * @return 资源列表
     */
    List<SysPermission> listPermissionsByRoles(@Param("roleIds") List<Integer> roleIds);
}
