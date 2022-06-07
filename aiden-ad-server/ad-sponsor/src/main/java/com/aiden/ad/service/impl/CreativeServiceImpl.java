package com.aiden.ad.service.impl;

import com.aiden.ad.dao.CreativeRepository;
import com.aiden.ad.entity.Creative;
import com.aiden.ad.service.ICreativeService;
import com.aiden.ad.vo.CreativeRequest;
import com.aiden.ad.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-07 23:49
 **/
@Service
public class CreativeServiceImpl implements ICreativeService {

    @Autowired
    private CreativeRepository creativeRepository;

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {
        Creative creative = creativeRepository.save(request.convertToEntity());

        return new CreativeResponse(creative.getId(), creative.getName());
    }

}
