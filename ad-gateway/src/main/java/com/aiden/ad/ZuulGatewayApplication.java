package com.aiden.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-05 18:40
 **/
@EnableZuulProxy
@SpringBootApplication
public class ZuulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
