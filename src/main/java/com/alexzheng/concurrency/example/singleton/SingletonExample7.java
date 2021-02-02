package com.alexzheng.concurrency.example.singleton;

import com.alexzheng.concurrency.annotation.Recommend;
import com.alexzheng.concurrency.annotation.ThreadSafe;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 15:33
 * @Annotation 枚举模式--最安全的
 */
@ThreadSafe
@Recommend
//推荐使用，相比懒汉模式在安全性上更有保证/ 相比饿汉模式，它可以在实际调用的时候才进行初始化
public class SingletonExample7{

    //私有构造函数
    private SingletonExample7(){

    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    // JVM保证该方法绝对只调用一次
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }

}
