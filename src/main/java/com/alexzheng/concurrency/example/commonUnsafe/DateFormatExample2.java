package com.alexzheng.concurrency.example.commonUnsafe;

import com.alexzheng.concurrency.annotation.NotThreadSafe;
import com.alexzheng.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author Alex Zheng
 * @Date 2021/2/4 10:24
 * @Annotation
 */
@Slf4j
@ThreadSafe
public class DateFormatExample2 {

    //该测试类运行会报错，原因是SimpleDateFormat是线程不安全对象
    //改造：使用堆栈封闭原则
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行线程数
    public static int threadTotal = 200;

    //计数

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //允许一次运行有200个线程
        final Semaphore semaphore = new Semaphore(threadTotal);
        //倒计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
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
    }

    private static void update(){
        try {
            //使用SimpleDateFormat这个变量的时候每次都要声明一个新的变量来使用。这样不会出现线程不安全所带来的异常
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20180208");
        } catch (ParseException e) {
            log.error("parse Exception!",e);
            e.printStackTrace();
        }
    }

}
