package com.starQeem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.starQeem.mapper.userMapper;
import com.starQeem.pojo.user;
import com.starQeem.service.deviceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@SpringBootTest
class DeviceApplicationTests {

    @Resource
    private deviceService deviceService;
    @Resource
    private userMapper userMapper;
    @Test
    void query() {
       deviceService.list().forEach(System.out::println);
    }
    @Test
    void testUser(){
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("admin".getBytes());
        QueryWrapper<user> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "admin").eq("password", md5DigestAsHex);
        user user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }


}
