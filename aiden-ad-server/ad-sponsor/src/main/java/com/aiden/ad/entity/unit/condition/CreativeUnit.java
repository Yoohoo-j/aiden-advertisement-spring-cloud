package com.aiden.ad.entity.unit.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 20:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creative_unit")
public class CreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;


    @Basic
    @Column(name = "creative_id", nullable = false)
    private Long creativeId;

    public CreativeUnit(Long unitId, Long creativeId) {
        this.unitId = unitId;
        this.creativeId = creativeId;
    }
}
