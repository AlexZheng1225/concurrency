package com.alexzheng.concurrency.example.publish;

import com.alexzheng.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 12:10
 * @Annotation
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    //通过public访问级别发布了类的域 导致类的对象不安全
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        //通过创建对象并获取应用得到并修改内部数据的值
        unsafePublish.getStates()[0] = "d"; //导致数据不完全确定
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }

}
