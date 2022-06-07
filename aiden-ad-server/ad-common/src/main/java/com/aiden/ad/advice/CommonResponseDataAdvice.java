package com.aiden.ad.advice;

import com.aiden.ad.annotation.IgnoreResponseAdvice;
import com.aiden.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: aiden-ad-spring-cloud
 * @description:
 * @author: Mr.Jiang
 * @create: 2022-06-05 19:23
 **/
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * @Description: 是否支持拦截
     * @Param [methodParameter, aClass]
     * @return: boolean
     * @Author: jiangyunhao
     * @Date: 2022/6/5 19:24
     * @throws
    */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){return false;}
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){return false;}
        return true;
    }

    /**
     * @Description: 在写入响应之前
     * @Param [o, methodParameter, mediaType, aClass, serverHttpRequest, serverHttpResponse]
     * @return: java.lang.Object
     * @Author: jiangyunhao
     * @Date: 2022/6/5 19:24
     * @throws
    */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(0, "");
        if (o == null){
            return response;
        } else if (o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        } else {
            response.setData(o);
        }
        return response;
    }
}
