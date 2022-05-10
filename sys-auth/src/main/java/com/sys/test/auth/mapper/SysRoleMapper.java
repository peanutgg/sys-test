package com.sys.test.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.test.auth.po.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product Dao层
 *
 * @author JAVA日知录
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据用户获取对应的角色列表
     *
     * @param userId 用户id
     * @return List<SysRole>
     */
    List<SysRole> listRolesByUserId(@Param("userId") Integer userId);
}
