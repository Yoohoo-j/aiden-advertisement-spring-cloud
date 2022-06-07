package com.aiden.ad.service;

import com.aiden.ad.entity.AdPlan;
import com.aiden.ad.exception.AdException;
import com.aiden.ad.vo.AdPlanGetRequest;
import com.aiden.ad.vo.AdPlanRequest;
import com.aiden.ad.vo.AdPlanResponse;

import java.util.List;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 21:58
 **/
public interface IAdPlanService {

    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;

    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;

    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
