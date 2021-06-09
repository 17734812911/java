package com.ct.springsecurity.controller.jump;

import com.ct.springsecurity.service.MethodELService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;

@Controller

public class JumpController {

    @Resource
    MethodELService methodELService;

    // 日志管理
    @GetMapping("/syslog")
    public String showOrder() {
        return "syslog";
    }

    // 用户管理
    @GetMapping("/sysuser")
    public String addOrder() {
        return "sysuser";
    }

    // 具体业务一
    @GetMapping("/biz1")
    public String updateOrder() {

        // methodELService.findAll();
        // methodELService.findOne();
        // List<Integer> ids = new ArrayList<>();
        // ids.add(1);
        // ids.add(2);
        // methodELService.delete(ids,null);
        // List<PersionDemo> pds = methodELService.findAllPD();

        return "biz1";
    }

    // 具体业务二
    @GetMapping("/biz2")
    public String deleteOrder() {
        return "biz2";
    }

}
