package com.alexzheng.concurrency.example.publish;

import com.alexzheng.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 12:12
 * @Annotation
 */
@Slf4j
@NotThreadSafe
public class Escape {
    private int thisCanBeEscape = 0;

    public Escape () {
        new InnerClass();
    }

    //私有内部类
    private class InnerClass {
        //对象没被正确构造之前就会被发布 会有不安全的因素
        //this应用逸出
        //在对象未完成构造之前不能将其发布
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
