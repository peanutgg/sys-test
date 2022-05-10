package com.sys.test.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.test.auth.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Product Dao层
 *
 * @author JAVA日知录
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser selectByUserName(@Param("userName") String userName);

    SysUser selectByMobile(@Param("mobile") String mobile);
}
