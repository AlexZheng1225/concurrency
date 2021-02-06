package com.alexzheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Alex Zheng
 * @Date 2021/2/6 22:01
 * @Annotation
 */
@Slf4j
public class SemaphoreExample2 {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    /**
                     * 一次获取三个许可，在此处的话执行的效果就是一秒只有一个线程被执行
                     * 因为其一次或许三个许可
                     */
                    semaphore.acquire(3); //获取多个许可
                    test(threadNum);
                    semaphore.release(3); //释放多个许可
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
