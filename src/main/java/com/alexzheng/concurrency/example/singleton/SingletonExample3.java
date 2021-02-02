package com.alexzheng.concurrency.example.singleton;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 14:35
 * @Annotation
 */

import com.alexzheng.concurrency.annotation.NotRecommend;
import com.alexzheng.concurrency.annotation.NotThreadSafe;
import com.alexzheng.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 【线程安全版】
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend //不推荐——加了synchronized会导致性能下降，性能开销太大
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3(){
        //用于做资源的处理
    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态工厂方法获取单例对象
    //单线程下代码没问题，多线程下代码可能会导致线程不安全 if循环中
    public static synchronized SingletonExample3 getInstance(){
        if (instance==null){
            instance = new SingletonExample3();
        }
        return instance;
    }

}
