package com.alexzheng.concurrency.example.concurrent;

import com.alexzheng.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author Alex Zheng
 * @Date 2021/2/4 10:39
 * @Annotation ConcurrentSkipListSet
 */
@Slf4j
@ThreadSafe //这里的线程安全指的是最基础的add/remove操作，xxxAll批量操作无法保证不被其他线程打断(例如addAll是调用add方法的)
public class ConcurrentSkipListSetExample {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //允许一次运行有200个线程
        final Semaphore semaphore = new Semaphore(threadTotal);
        //倒计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                //将countDownLatch值减一
                countDownLatch.countDown();
            });
        }
        //等待countDownLatch值减为0
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",set.size());
    }

    private static void update(int i){
        set.add(i);
    }

}
