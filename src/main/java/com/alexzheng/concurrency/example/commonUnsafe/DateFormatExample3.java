package com.alexzheng.concurrency.example.commonUnsafe;

import com.alexzheng.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
public class DateFormatExample3 {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行线程数
    public static int threadTotal = 200;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

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
    }

    private static void update(int i){
        log.info("{},{}",i,DateTime.parse("20180208",dateTimeFormatter).toDate());
    }

}
