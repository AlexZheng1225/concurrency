package com.alexzheng.concurrency.example.syncContainer;

import com.alexzheng.concurrency.annotation.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

/**
 * @Author Alex Zheng
 * @Date 2021/2/4 10:39
 * @Annotation
 */

/**
 * 如果使用Iterator遍历或者foreach遍历集合的时候，不要再操作的过程中进行remove操作
 * 若要删除，先做好标记，等遍历完再删除
 * --------------------
 * foreach底层也是迭代器
 * --------------------
 * 在多线程情况下：在我们使用迭代器迭代的时候，使用synchronized或者lock，或者一些并发容器来进行保证线程安全
 * 实际开发中多用并发容器代替同步容器
 */
public class VectorExample3 {

    //三个方法都是遍历集合，并对指定元素进行删除操作。

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){ //foreach
        for (Integer i:v1){
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){ //iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //success
    private static void test3(Vector<Integer> v1){
        for (int i=0;i<v1.size();i++){
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test1(vector);
//        test2(vector);
//        test3(vector);
    }

}
