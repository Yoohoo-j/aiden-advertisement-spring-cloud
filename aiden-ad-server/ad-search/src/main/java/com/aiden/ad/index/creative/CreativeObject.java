package com.aiden.ad.index.creative;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-18 15:19
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeObject {

    private Long adId;
    private String name;
    private Integer type;
    private Integer height;
    private Integer width;
    private Integer auditStatus;
    private String adUrl;

    public void update(CreativeObject creativeObject){
        if (null != creativeObject.getAdId()){
            this.adId = creativeObject.getAdId();
        }
        if (null != creativeObject.getName()){
            this.name = creativeObject.getName();
        }
        if (null != creativeObject.getType()){
            this.type = creativeObject.getType();
        }
        if (null != creativeObject.getHeight()){
            this.height = creativeObject.getHeight();
        }
        if (null != creativeObject.getWidth()){
            this.width = creativeObject.getWidth();
        }
        if (null != creativeObject.getAuditStatus()){
            this.auditStatus = creativeObject.getAuditStatus();
        }
        if (null != creativeObject.getAdUrl()){
            this.adUrl = creativeObject.getAdUrl();
        }


    }
}
