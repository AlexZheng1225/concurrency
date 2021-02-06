package com.alexzheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Alex Zheng
 * @Date 2021/2/6 22:01
 * @Annotation
 */
@Slf4j
public class SemaphoreExample1 {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3); //在下面的循环中，每一秒只能放行三个
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    //获取一个许可
                    semaphore.acquire();
                    test(threadNum);
                    //执行完释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }

}
