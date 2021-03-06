package com.alexzheng.concurrency.example.commonUnsafe;

import com.alexzheng.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Alex Zheng
 * @Date 2021/2/4 10:39
 * @Annotation
 */
@Slf4j
@NotThreadSafe
public class HashMapExample {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行线程数
    public static int threadTotal = 200;

    private static final Map<Integer,Integer> hashMap = new HashMap<>();

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
        log.info("size:{}",hashMap.size());
    }

    private static void update(int i){
        //HashMap的put操作线程不安全
        hashMap.put(i,i);
    }

}
