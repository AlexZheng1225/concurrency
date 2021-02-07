package com.alexzheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 13:57
 * @Annotation newScheduledThreadPool
 */
@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {
        //
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
            //延迟一秒，然后每隔三秒执行任务
        },1,3,TimeUnit.SECONDS);

//        executorService.shutdown();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },new Date(),5*400);
    }
}
