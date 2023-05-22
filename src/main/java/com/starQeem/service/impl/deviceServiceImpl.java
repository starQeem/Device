package com.starQeem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.starQeem.mapper.deviceMapper;
import com.starQeem.pojo.device;
import com.starQeem.service.deviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class deviceServiceImpl extends ServiceImpl<deviceMapper, device> implements deviceService {
    @Resource
    private deviceMapper deviceMapper;
    private final static int PAGE_SIZE = 8;
    @Override
    public PageInfo<device> pageSearchDevice(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page,pageSize);
        QueryWrapper<device> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<device> deviceList = deviceMapper.selectList(queryWrapper);
        return new PageInfo<>(deviceList,PAGE_SIZE);
    }
    @Override
    public device addDevice(device device) {
        QueryWrapper<device> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", device.getName());
        return deviceMapper.selectOne(queryWrapper);
    }
}
