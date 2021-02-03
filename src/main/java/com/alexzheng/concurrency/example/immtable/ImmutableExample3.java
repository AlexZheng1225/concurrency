package com.alexzheng.concurrency.example.immtable;

import com.alexzheng.concurrency.annotation.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
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
public class ImmutableExample3 {

    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4); //这里的传值有点区别 是key-value的

    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().
            put(1,2).put(3,4).put(5,6).build(); //这里的传值有点区别 是key-value的

    public static void main(String[] args) {
        //都是不可变对象，线程安全
//        map.put(1,4);
//        map2.put(1,4);
        System.out.println(map2.get(3));
    }

}
