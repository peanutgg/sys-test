package com.sys.test.service.a.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sys.test.service.a.entity.UserInfo;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

@Mapper
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    int saveUser(@Param("userInfo") UserInfo userInfo);

    List<UserInfo> selectUsers(@Param("id")Integer id);
}
