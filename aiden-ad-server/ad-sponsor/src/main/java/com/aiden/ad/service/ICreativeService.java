package com.aiden.ad.service;

import com.aiden.ad.vo.CreativeRequest;
import com.aiden.ad.vo.CreativeResponse;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-07 23:38
 **/
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
