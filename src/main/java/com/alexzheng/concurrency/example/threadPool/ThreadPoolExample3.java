package com.alexzheng.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 13:57
 * @Annotation newSingleThreadExecutor
 */
@Slf4j
public class ThreadPoolExample3 {

    public static void main(String[] args) {
        //创建一个单个线程的线程池 所以按顺序
        ExecutorService executorService = Executors.newSingleThreadExecutor();
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
