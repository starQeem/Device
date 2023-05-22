package com.starQeem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.starQeem.mapper.userMapper;
import com.starQeem.pojo.user;
import com.starQeem.service.userService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class userServiceImpl extends ServiceImpl<userMapper, user> implements userService {
    @Resource
    private userMapper userMapper;
    @Override
    public user queryUser(String username, String password) {
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("password", md5DigestAsHex);
        return userMapper.selectOne(queryWrapper);
    }
}
