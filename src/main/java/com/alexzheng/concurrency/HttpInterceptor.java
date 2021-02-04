package com.alexzheng.concurrency;

import com.alexzheng.concurrency.example.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Alex Zheng
 * @Date 2021/2/3 15:21
 * @Annotation
 */
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandler");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //接口处理完后调用remove()方法；
        RequestHolder.remove();
        log.info("afterCompletion");
        return;
    }
}
