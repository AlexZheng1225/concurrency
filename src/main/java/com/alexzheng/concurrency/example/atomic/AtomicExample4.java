package com.alexzheng.concurrency.example.atomic;

import com.alexzheng.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 7:43
 * @Annotation AtomicReference
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    public static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);   //2
        count.compareAndSet(0,1);   //no
        count.compareAndSet(1,3);   //no
        count.compareAndSet(2,4);   //4
        count.compareAndSet(3,5);   //no
        log.info("count:{}",count.get()); //结果为4
    }

}
