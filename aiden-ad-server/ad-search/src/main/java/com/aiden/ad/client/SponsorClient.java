package com.aiden.ad.client;

import com.aiden.ad.client.vo.AdPlan;
import com.aiden.ad.client.vo.AdPlanGetRequest;
import com.aiden.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-11 00:05
 **/
@FeignClient(value = "eureka-client-ad-sponsor", fallback = SponsorClientHystrix.class) // fallback 使用hystrix 实现服务降级
public interface SponsorClient {

    @RequestMapping(value = "/ad-sponsor/get/adPlan")
    CommonResponse<List<AdPlan>> getAdPlans(@RequestBody AdPlanGetRequest request);
}
