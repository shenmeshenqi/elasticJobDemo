package com.zzh.elasticjob.demo.controller;

import com.zzh.elasticjob.demo.config.ConfigJobInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j(topic = "controllerTest")
@Controller
public class TestController {
    @Autowired
    private ConfigJobInit configJobInit;
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/start")
    @ResponseBody
    public String hello(){
        log.info("开始启动作业");
        configJobInit.initJobs();
        log.info("当前作业结束");
        return "ok";
    }
}
