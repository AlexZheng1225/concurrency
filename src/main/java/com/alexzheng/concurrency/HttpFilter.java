package com.alexzheng.concurrency;

import com.alexzheng.concurrency.example.threadlocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author Alex Zheng
 * @Date 2021/2/3 15:14
 * @Annotation 过滤器
 */
@Slf4j
public class HttpFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        log.info("do filter,{},{}",Thread.currentThread().getId(),request.getServletPath());
        //调用add方法
        RequestHolder.add(Thread.currentThread().getId());
        //如果filter不想拦截请求 想继续处理，则能保证拦截器执行完请求继续被处理
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
