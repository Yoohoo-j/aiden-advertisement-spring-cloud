package com.aiden.ad.service.impl;

import com.aiden.ad.constant.Constants;
import com.aiden.ad.dao.AdUserRepository;
import com.aiden.ad.entity.AdUser;
import com.aiden.ad.exception.AdException;
import com.aiden.ad.service.IUserService;
import com.aiden.ad.utils.CommonUtils;
import com.aiden.ad.vo.CreateUserRequest;
import com.aiden.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 21:48
 **/
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository userRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = userRepository.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())));

        return new CreateUserResponse(newUser.getId(), newUser.getUsername(),
                newUser.getToken(), newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
