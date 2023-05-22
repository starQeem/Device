package com.starQeem.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class user {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
}
