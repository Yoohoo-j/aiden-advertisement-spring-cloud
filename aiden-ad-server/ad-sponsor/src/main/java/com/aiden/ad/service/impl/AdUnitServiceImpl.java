package com.aiden.ad.service.impl;

import com.aiden.ad.constant.Constants;
import com.aiden.ad.dao.AdPlanRepository;
import com.aiden.ad.dao.AdUnitRepository;
import com.aiden.ad.dao.CreativeRepository;
import com.aiden.ad.dao.unit.condition.AdUnitDistrictRepository;
import com.aiden.ad.dao.unit.condition.AdUnitItRepository;
import com.aiden.ad.dao.unit.condition.AdUnitKeywordRepository;
import com.aiden.ad.dao.unit.condition.CreativeUnitRepository;
import com.aiden.ad.entity.AdPlan;
import com.aiden.ad.entity.AdUnit;
import com.aiden.ad.entity.unit.condition.AdUnitDistrict;
import com.aiden.ad.entity.unit.condition.AdUnitIt;
import com.aiden.ad.entity.unit.condition.AdUnitKeyword;
import com.aiden.ad.entity.unit.condition.CreativeUnit;
import com.aiden.ad.exception.AdException;
import com.aiden.ad.service.IAdUnitService;
import com.aiden.ad.vo.*;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 22:43
 **/
@Service
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository planRepository;
    @Autowired
    private AdUnitRepository unitRepository;
    @Autowired
    private AdUnitKeywordRepository unitKeywordRepository;
    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;
    @Autowired
    private AdUnitItRepository adUnitItRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());
        if (!adPlan.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldAdUnit = unitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());

        if (oldAdUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newAdUnit = unitRepository.save(new AdUnit(request.getPlanId(), request.getUnitName(),
                request.getPositionType(), request.getBudget()));
        return new AdUnitResponse(newAdUnit.getId(), newAdUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if (!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> unitKeywords.add(new AdUnitKeyword(i.getUnitId(), i.getKeyword())));
            ids = unitKeywordRepository.saveAll(unitKeywords).stream().map(AdUnitKeyword::getId).collect(Collectors.toList());
        }

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<Long> unitIds = request.getUnitIts().stream().map(AdUnitItRequest.UnitIt::getUnitId).collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> unitIts = new ArrayList<>();
        request.getUnitIts().forEach(i -> unitIts.add(new AdUnitIt(i.getUnitId(), i.getItTag())));
        List<Long> ids = adUnitItRepository.saveAll(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());
        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<Long> unitIds = request.getUnitDistricts().stream().map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitDistrict> unitDistricts = new ArrayList<>();
        request.getUnitDistricts().forEach(d -> unitDistricts.add(new AdUnitDistrict(d.getUnitId(), d.getProvince(), d.getCity())));
        List<Long> ids = adUnitDistrictRepository.saveAll(unitDistricts).stream().map(AdUnitDistrict::getId).collect(Collectors.toList());
        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {
        List<Long> unitIDs = request.getUnitItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIDs) && !isRelatiedCretiveExist(creativeIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(new CreativeUnit(i.getCreativeId(), i.getUnitId())));
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream().map(CreativeUnit::getId).collect(Collectors.toList());

        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatiedCretiveExist(List<Long> creativeIds){
        if (CollectionUtils.isEmpty(creativeIds)){
            return false;
        }

        return creativeRepository.findAllById(creativeIds).size() == new HashSet<>(creativeIds).size();
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if (CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        return unitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }
}
