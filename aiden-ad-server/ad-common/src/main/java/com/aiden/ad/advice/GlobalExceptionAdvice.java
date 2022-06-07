package com.aiden.ad.advice;

import com.aiden.ad.exception.AdException;
import com.aiden.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-05 19:34
 **/
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException ex){

        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }
}
