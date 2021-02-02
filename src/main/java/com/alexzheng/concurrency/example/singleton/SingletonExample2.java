package com.alexzheng.concurrency.example.singleton;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 14:35
 * @Annotation
 */

import com.alexzheng.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候进行创建，因此能保证线程安全
 * 不足：若构造函数中有太多数据处理会导致该类在加载的过程中加载的特别慢
 * 要使用的话考虑：1：私有构造函数在实现时没有太多处理 2：保证该类实例后会被使用，避免造成资源浪费
 */
@ThreadSafe //线程安全
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2(){
        //用于做资源的处理
    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法获取单例对象
    public static SingletonExample2 getInstance(){
        return instance;
    }

}
