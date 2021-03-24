package com.example.demo.lx.learn.thread.controller;

import com.example.demo.lx.learn.thread.service.AysncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:15
 */
@Controller
@RequestMapping("/cs/task")
public class AysncTaskController {

    @Autowired
    private AysncTaskService aysncTaskService;

    @RequestMapping("/task1")
    @ResponseBody
    public String task1() {

        try {
            for (int i=0 ; i < 100 ; i++){
                aysncTaskService.excuteAysncTask(i);
                CompletableFuture completableFuture = aysncTaskService.excuteAysncTask2(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "ok!";
    }
}
