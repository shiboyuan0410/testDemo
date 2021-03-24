package com.example.demo.lx.learn.thread.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: shiboyuan
 * @Date: 2021/3/23 15:11
 */
@Service
@Slf4j
public class AysncTaskService {

    /**
     * @Async注解中指定线程池名,异步调用线程池去执行任务
     */
    @Async("asynTaskExecutor")
    public void excuteAysncTask(int i) throws InterruptedException{
        log.info("asynTaskExecutor1执行异步线程:"+i);
        Thread.sleep(500L);
    }


    /**
     * @Async注解中指定线程池名,异步调用线程池去执行任务
     */
    @Async("asynTaskExecutor")
    public CompletableFuture excuteAysncTask2(int i) throws InterruptedException{
        log.info("asynTaskExecutor2执行异步线程:"+i);
        Thread.sleep(500L);

        return CompletableFuture.completedFuture(i);
    }
}
