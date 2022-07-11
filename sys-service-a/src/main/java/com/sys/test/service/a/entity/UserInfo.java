package com.sys.test.service.a.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("user_info")
public class UserInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    public UserInfo(String name) {
        this.name = name;
    }

    public UserInfo() {
    }

    public UserInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
