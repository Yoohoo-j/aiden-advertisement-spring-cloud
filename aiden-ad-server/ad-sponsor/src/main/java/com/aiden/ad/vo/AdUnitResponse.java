package com.aiden.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 22:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdUnitResponse {

    private Long id;
    private String unitName;

}
