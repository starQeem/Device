package com.starQeem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.starQeem.pojo.user;

public interface userService extends IService<user> {
    user queryUser(String username, String password);
}
