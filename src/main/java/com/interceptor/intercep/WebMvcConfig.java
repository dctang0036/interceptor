package com.interceptor.intercep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*@Autowired
    private OneInterceptor oneInterceptor;*/

    /**
     * 配置式的拦截器
     */
    /*@Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径 和 排除拦截路径
        // 拦截器添加到controller层
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/job/**");
    }*/

    /**
     * 自定义拦截器，配置和配置式的拦截器一样
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册自定义拦截器，添加拦截路径 和 排除拦截路径
        // 拦截器添加到controller层
        registry.addInterceptor(new AvoidDuplicateSubmissionInterceptor()).addPathPatterns("/toindex/**", "/commit/**");
    }
}
