package com.fatih.petking.infrastructure.interceptor.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LogExecutionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogExecutionInterceptor.class);

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        try {
            if (handler instanceof HandlerMethod handlerMethod) {
                request.setAttribute("requestStartTime", System.currentTimeMillis());
                logger.info("Starting controller method for {}.{}",
                        handlerMethod.getBeanType().getSimpleName(),
                        handlerMethod.getMethod().getName());
            }
        } catch (Exception e) {
            logger.error("Caught an exception while executing handler method", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        try {
            if (handler instanceof HandlerMethod handlerMethod) {
                logger.info("Completed controller method for {}.{} takes {} ms",
                        handlerMethod.getBeanType().getSimpleName(),
                        handlerMethod.getMethod().getName(),
                        (System.currentTimeMillis() - (Long) request.getAttribute("requestStartTime")));
            }
        } catch (Exception e) {
            logger.error("Caught an exception while executing handler method", e);
        }
    }
}
