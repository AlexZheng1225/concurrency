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
public class SynchronizedExample1 {

    //修饰代码块
    public void test1(int j){
        synchronized(this){
            for (int i=0;i<10;i++){
                log.info("test1 - {} -- {}",j,i);
            }
        }
    }

    //修饰一个方法 [注：如果说本类是父类，子类调用父类这个方法将不会带上synchronized关键字,子类想要实现synchronized需要自己进行标识]
    public synchronized void test2(int j){
            for (int i=0;i<10;i++){
                log.info("test2 - {} -- {}",j,i);
            }
    }

    //上面两个方法作用对象都是调用的对象

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
    }

}
