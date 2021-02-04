package com.alexzheng.concurrency.example.threadlocal;

/**
 * @Author Alex Zheng
 * @Date 2021/2/3 15:09
 * @Annotation 希望绑定到线程上的信息
 */
public class RequestHolder {

    //存个Long值打印线程ID(业务中存放User)
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //add在请求进入服务器但是还未进入相关方法的时候进行调用处理(可以使用filter)
    public static void add(long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    //释放类 不然会造成内存泄漏
    //在接口真正处理完后进行处理(Interceptor)
    public static void remove(){
        requestHolder.remove();
    }

}
