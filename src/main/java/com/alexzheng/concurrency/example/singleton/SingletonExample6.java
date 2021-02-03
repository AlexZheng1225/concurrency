package com.alexzheng.concurrency.example.singleton;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 14:35
 * @Annotation
 */

import com.alexzheng.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式的另一种写法
 * 单例实例在类装载的时候进行创建，因此能保证线程安全
 */
@ThreadSafe //线程安全
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6(){
        //用于做资源的处理
    }

    //单例对象
    private static SingletonExample6 instance = null;

    //静态代码块
    static{
        instance = new SingletonExample6();
    }

    //静态工厂方法获取单例对象
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        //hashcode一致 说明同个对象
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
