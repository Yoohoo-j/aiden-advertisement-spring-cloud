package com.aiden.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 22:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitItResponse {

    private List<Long> ids;

}
