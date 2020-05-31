package com.interceptor.intercep;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AvoidDuplicateSubmissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        AvoidDuplicateSub annotation = method.getAnnotation(AvoidDuplicateSub.class);

        if (annotation != null) {
            boolean needSaveSession = annotation.needSaveToken();
            String tokenName = annotation.tokenName();
            if (needSaveSession) {
                // 首次拦截，创建token
                request.getSession().setAttribute(tokenName, TokenProccessor.getInstance().makeToken());
            }

            // 第二次拦截，移除token
            boolean needRemoveSession = annotation.needRemoveToken();
            if (needRemoveSession) {
                // 判断是否重复提交
                if (isRepeatSubmit(request, tokenName)) {
                    response.setStatus(900);
                    return false;
                }
                request.getSession(false).removeAttribute(tokenName);
            }
        }
        return true;
    }

    private boolean isRepeatSubmit(HttpServletRequest request, String tokenName) {
        String serverToken = (String) request.getSession(false).getAttribute(tokenName);
        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getHeader(tokenName);
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }
}
