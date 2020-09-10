package com.apiversionsecurity.demo.controller;

import com.apiversionsecurity.demo.annotation.ApiVersion;
import com.apiversionsecurity.demo.util.ServletUtil;
import com.apiversionsecurity.demo.util.SessionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{version}/home")
public class HomeController {

    //匹配版本v1的访问
    @ApiVersion("1")
    @GetMapping
    @RequestMapping("/home")
    public String home01(@PathVariable String version) {
        return "home v1 : version:" + version;
    }

    //匹配版本v2的访问
    @ApiVersion("2.0")
    @GetMapping
    @RequestMapping("/home")
    public String home02(@PathVariable String version) {
        String username = SessionUtil.getCurrentUserName();
        String url = ServletUtil.getRequest().getRequestURL().toString();
        return "home v2 version: " + version+":username:"+username+";url:"+url;
    }

    //匹配版本v1.5-2.0的访问
    @ApiVersion("1.5")
    @GetMapping
    @RequestMapping("/home")
    public String home15(@PathVariable String version) {
        return "home v1.5 version: " + version;
    }

}