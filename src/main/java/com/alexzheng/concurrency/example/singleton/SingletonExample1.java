package com.alexzheng.concurrency.example.singleton;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 14:35
 * @Annotation
 */

import com.alexzheng.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有构造函数
    private SingletonExample1(){
        //用于做资源的处理
    }

    //单例对象
    private static SingletonExample1 instance = null;

    //静态工厂方法获取单例对象
    //单线程下代码没问题，多线程下代码可能会导致线程不安全 if循环中
    public static SingletonExample1 getInstance(){
        if (instance==null){
            instance = new SingletonExample1();
        }
        return instance;
    }

}
