package com.alexzheng.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Alex Zheng
 * @Date 2021/2/2 7:26
 * @Annotation
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
