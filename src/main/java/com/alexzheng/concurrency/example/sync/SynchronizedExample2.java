package com.alexzheng.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 10:52
 * @Annotation
 */
@Slf4j
public class SynchronizedExample2 {

    //修饰类
    public void test1(int j){
        synchronized(SynchronizedExample2.class){
            for (int i=0;i<10;i++){
                log.info("test1 - {} -- {}",j,i);
            }
        }
    }

    //修饰一个staic方法
    public static synchronized void test2(int j){
            for (int i=0;i<10;i++){
                log.info("test2 - {} -- {}",j,i);
            }
    }

    //上面两个方法作用对象是这个类的所有对象

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test1(1);
        });
        executorService.execute(()->{
            example2.test1(2);
        });
    }

}
