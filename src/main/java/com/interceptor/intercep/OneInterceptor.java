package com.interceptor.intercep;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
* @Description: 自定义拦截器
* @author: dctang
* @date: 2020/5/30 9:38
*/
@Configuration
public class OneInterceptor implements HandlerInterceptor {

    public OneInterceptor() {
        // 无参构造
    }
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("==================");
        String queryuString = request.getQueryString();
        String requestUrl = request.getRequestURI().toString();
        System.out.println("拦截器1，入参" + queryuString + ", 访问地址，" + requestUrl);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        System.out.println("拦截器2");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("拦截器3");
    }
}
