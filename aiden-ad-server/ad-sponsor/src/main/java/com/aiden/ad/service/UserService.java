package com.aiden.ad.service;

import com.aiden.ad.exception.AdException;
import com.aiden.ad.vo.CreateUserRequest;
import com.aiden.ad.vo.CreateUserResponse;

public interface UserService {

    /**
     * @Description: 创建用户
     * @Param [request]
     * @return: com.aiden.ad.vo.CreateUserResponse
     * @Author: jiangyunhao
     * @Date: 2022/6/6 21:48
     * @throws
    */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
