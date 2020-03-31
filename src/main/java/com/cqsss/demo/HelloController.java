package com.cqsss.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author: @肖朋伟CSDN
 * @create: 2018-11-18
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello，world！";
    }
}