package com.fatih.petking.infrastructure.interceptor.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.UUID;

public class CorrelationIdInterceptor implements AsyncHandlerInterceptor {

    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler){
        var correlationId = getCorrelationId(request);
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
        return true;
    }

    private String getCorrelationId(HttpServletRequest request) {
        var correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = UUID.randomUUID().toString();
        }
        return correlationId;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex){
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
    }
}
