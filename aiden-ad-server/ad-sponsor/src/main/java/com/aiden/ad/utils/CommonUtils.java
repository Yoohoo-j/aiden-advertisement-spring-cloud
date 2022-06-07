package com.aiden.ad.utils;

import com.aiden.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-06 21:54
 **/
public class CommonUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"};

    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String dateString) throws AdException{
        try {
            return DateUtils.parseDate(dateString, parsePatterns);
        } catch (ParseException e) {
            throw new AdException(e.getMessage());
        }
    }
}
