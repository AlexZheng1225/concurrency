package com.alexzheng.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 7:14
 * @Annotation 用于标记[线程不安全]的类或写法
 */
//下面指我们注解的目标 ElementType类可以单独研究一下
@Target(ElementType.TYPE)
//下面注解表示该注解只在源码存在
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";

}
