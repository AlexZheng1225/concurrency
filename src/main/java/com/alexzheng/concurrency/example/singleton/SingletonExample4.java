package com.alexzheng.concurrency.example.singleton;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 14:35
 * @Annotation
 */

import com.alexzheng.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式 --》 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4(){
        //用于做资源的处理
    }

    //为何该类线程不安全？
    //1.memory = allocate() 分配对象内存空间
    //2.ctorInstance() 初始化对象
    //3.instance = memory 设置instance指向刚分配的内存

    //多线程下JVM和CPU优化，可能发生指令重排

    //1.memory = allocate() 分配对象内存空间
    //3.instance = memory 设置instance指向刚分配的内存
    //2.ctorInstance() 初始化对象

    //再回去看代码如果线程A走1-3，走到3这一步，instance还未初始化时，线程B也进入双重检测机制，发现这个时候发现instance有值
    //则直接返回。而导致返回了一个未初始化的值 一旦调用就会报错

    //单例对象
    private static SingletonExample4 instance = null;

    //静态工厂方法获取单例对象
    public static SingletonExample4 getInstance(){
        if (instance==null){ //双重检测机制       //B
            synchronized (SingletonExample4.class){ //同步锁
                if (instance == null){
                    instance = new SingletonExample4(); //A-3
                }
            }
        }
        return instance;
    }

}
