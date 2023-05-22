package com.starQeem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.starQeem.pojo.device;

public interface deviceService extends IService<device> {
    PageInfo<device> pageSearchDevice(Integer page, Integer pageSize, String name);
    device addDevice(device device);
}
