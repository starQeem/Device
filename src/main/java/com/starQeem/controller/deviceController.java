package com.starQeem.controller;

import com.github.pagehelper.PageInfo;
import com.starQeem.mapper.deviceMapper;
import com.starQeem.pojo.device;
import com.starQeem.service.deviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

@Controller
@Slf4j
public class deviceController {
    @Resource
    private deviceService deviceService;
    @Resource
    private deviceMapper deviceMapper;
    private final static int PAGE_SIZE = 8;
    /*
     * 分页查询
     * */
    @GetMapping(value = {"/index/{page}", "/index"})
    public String search(@PathVariable(value = "page", required = false) Integer page, Model model, String name) {
        if (page == null) {
            page = 1;
        }
        if (name == null){
            name = "";
        }
        PageInfo<device> devicePageInfo = deviceService.pageSearchDevice(page, PAGE_SIZE, name);
        model.addAttribute("page", devicePageInfo);
        return "index";
    }
    /*
     * 跳转到添加页面
     * */
    @GetMapping("/index/add")
    public String addDevice(Model model) {
        model.addAttribute("device", new device());
        return "add";
    }
    /*
     * 添加
     * */
    @PostMapping("/index/add")
    public String addDeviceInput(device device, RedirectAttributes attribute) {
        device deviceByName = deviceService.addDevice(device);
        if (deviceByName != null) {
            attribute.addFlashAttribute("message", "不能添加重复设备!");
        } else {
            boolean success = deviceService.save(device);
            if (success) {
                attribute.addFlashAttribute("message", "添加成功");
            } else {
                attribute.addFlashAttribute("message", "添加失败");
            }
        }
        return "redirect:/index";
    }
    /*
     * 跳转到修改页面并回显
     * */
    @GetMapping("index/{id}/update")
    public String updateDevice(@PathVariable("id") Integer id, Model model) {
        device device = deviceMapper.selectById(id);
        model.addAttribute("device", device);
        return "update";
    }
    /*
     * 修改
     * */
    @PostMapping("index/update")
    public String updateDeviceInput(device device, RedirectAttributes attributes) {
        boolean success = deviceService.updateById(device);
        if (success) {
            attributes.addFlashAttribute("message", "修改成功!");
        } else {
            attributes.addFlashAttribute("message", "修改失败!");
        }
        return "redirect:/index";
    }
    /*
     * 删除
     * */
    @RequestMapping("/index/{id}/delete")
    public String deleteDevice(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        boolean success = deviceService.removeById(id);
        if (success) {
            attributes.addFlashAttribute("message", "删除成功!");
        } else {
            attributes.addFlashAttribute("message", "删除失败!");
        }
        return "redirect:/index";
    }
}
