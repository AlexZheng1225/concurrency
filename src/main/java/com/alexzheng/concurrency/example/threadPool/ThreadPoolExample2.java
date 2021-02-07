package com.alexzheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 13:57
 * @Annotation newFixedThreadPool
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        //给定3个线程数
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}",index);
                }
            });
        }
        executorService.shutdown();
    }
}
