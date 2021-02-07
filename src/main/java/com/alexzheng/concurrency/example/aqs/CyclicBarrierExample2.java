package com.alexzheng.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Author Alex Zheng
 * @Date 2021/2/7 8:07
 * @Annotation
 */
@Slf4j
public class CyclicBarrierExample2 {

    //告诉当前有多少个线程要进行同步等待
    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try{
            //等待线程 凑齐个数再执行
            barrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (BrokenBarrierException | TimeoutException e){
            log.warn("BrokenBarrierException",e);
        }
        log.info("{} continue.",threadNum);
    }
}
