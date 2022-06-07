package com.aiden.ad.constant;

import lombok.Getter;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 01:17
 **/
@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
