package com.aiden.ad.service.impl;

import com.aiden.ad.constant.CommonStatus;
import com.aiden.ad.constant.Constants;
import com.aiden.ad.dao.AdPlanRepository;
import com.aiden.ad.dao.AdUserRepository;
import com.aiden.ad.entity.AdPlan;
import com.aiden.ad.entity.AdUser;
import com.aiden.ad.exception.AdException;
import com.aiden.ad.service.IAdPlanService;
import com.aiden.ad.utils.CommonUtils;
import com.aiden.ad.vo.AdPlanGetRequest;
import com.aiden.ad.vo.AdPlanRequest;
import com.aiden.ad.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 22:12
 **/
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdUserRepository userRepository;

    @Autowired
    private AdPlanRepository planRepository;

    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        AdPlan newAdPlan = planRepository.save(new AdPlan(request.getUserId(), request.getPlanName(),
                CommonUtils.parseStringDate(request.getStartDate()), CommonUtils.parseStringDate(request.getEndDate())));
        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if (!request.valiate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if (!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        if (request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }
        if (request.getStartDate() != null){
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }
        if (request.getEndDate() != null){
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }

        plan.setUpdateTime(new Date());
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
