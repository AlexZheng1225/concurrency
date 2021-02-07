package com.alexzheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 10:47
 * @Annotation
 */
@Slf4j
public class FutureExample {

    static class myCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done.";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new myCallable());
        log.info("so something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
        executorService.shutdown();
    }
}
