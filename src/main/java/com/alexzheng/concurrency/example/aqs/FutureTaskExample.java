package com.alexzheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 10:53
 * @Annotation
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "Done.";
            }
        });

        new Thread(futureTask).start();
        log.info("so something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
