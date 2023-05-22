package com.starQeem.controller;

import com.starQeem.pojo.user;
import com.starQeem.service.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class userController {
    @Resource
    private userService userService;
    /*
    * 跳转到登录页面
    * */
    @GetMapping
    public String login(){
        return "login";
    }
    /*
    * 登录
    * */
    @PostMapping
    public String loginInput(RedirectAttributes attributes, String username, String password, HttpSession session){
        user user = userService.queryUser(username, password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(-1);
            return "redirect:/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误!");
            return "redirect:/";
        }
    }
    /*
    * 注销
    * */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }


}
