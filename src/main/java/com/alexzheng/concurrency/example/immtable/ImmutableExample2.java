package com.alexzheng.concurrency.example.immtable;

import com.alexzheng.concurrency.annotation.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * @Author Alex Zheng
 * @Date 2021/2/3 14:13
 * @Annotation
 */
@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        //通过Collections.unmodifiablexxx()包装过的对象不可变
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //基本类型变量在初始化之后值不能改变，而引用类型变量在类初始化之后不能更换引用，但是能更改引用对象的值
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));
    }

    private void test(final int a) {
//        a = 1;
    }

}
