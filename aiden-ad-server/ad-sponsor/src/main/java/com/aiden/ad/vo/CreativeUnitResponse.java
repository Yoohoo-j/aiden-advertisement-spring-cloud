package com.aiden.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-08 00:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitResponse {

    private List<Long> ids;

}
