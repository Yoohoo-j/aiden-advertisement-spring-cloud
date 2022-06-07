package com.aiden.ad.constant;

import lombok.Getter;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 21:03
 **/
@Getter
public enum CreativeMetriaType {

    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private int type;
    private String desc;

    CreativeMetriaType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
