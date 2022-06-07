package com.aiden.ad.controller;

import com.aiden.ad.exception.AdException;
import com.aiden.ad.service.IUserService;
import com.aiden.ad.vo.CreateUserRequest;
import com.aiden.ad.vo.CreateUserResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-08 00:48
 **/
@Slf4j
@RestController
public class UserOPCOntroller {

    @Autowired
    private IUserService userService;

    @PostMapping("/create/user")
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        return userService.createUser(request);
    }
}
