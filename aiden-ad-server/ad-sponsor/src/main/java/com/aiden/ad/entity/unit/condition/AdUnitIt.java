package com.aiden.ad.entity.unit.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 20:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ad_unit_it")
public class AdUnitIt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "it_tag", nullable = false)
    private String itTag;

    public AdUnitIt(Long unitId, String itTag){
        this.unitId = unitId;
        this.itTag = itTag;
    }
}
