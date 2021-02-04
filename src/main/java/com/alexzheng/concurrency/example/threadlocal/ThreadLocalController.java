package com.alexzheng.concurrency.example.threadlocal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alex Zheng
 * @Date 2021/2/3 17:20
 * @Annotation
 */
@RestController
@RequestMapping("/threadlocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public long test(){
        return RequestHolder.getId();
    }

}
