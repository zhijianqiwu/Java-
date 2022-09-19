package com.zmt.community.controller;

import com.zmt.community.util.CommunityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zmt
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public String hello(){
        return "hello zmt";
    }

    // cookie示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        // 创建cookie，必须带有字符串的key和value值，且一个cookie只能保存一对键值对
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        // 设置cookie生效的范围
        cookie.setPath("");
        // 设置cookie的生存时间,否则默认保存在内存，浏览器一关闭就消失
        cookie.setMaxAge(60 * 10);
        // 将cookie保存到请求对象中，发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    //@CookieValue("code")会在cookie中找到key值为code的cookie的值
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie";
    }

    // ajax示例
    @RequestMapping(path = "/ajax", method = RequestMethod.POST)
    @ResponseBody  //返回的是JSON字符串需要加上该注解
    public String testAjax(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return CommunityUtil.getJSONString(0, "操作成功!");
    }
}
