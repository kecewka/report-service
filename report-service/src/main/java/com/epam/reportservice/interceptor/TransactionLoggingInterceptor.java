package com.epam.reportservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class TransactionLoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LogManager.getLogger(TransactionLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String transactionId = request.getHeader("transactionId");
        String mdcTransactionId = MDC.get("transactionId");
        if(transactionId == null && mdcTransactionId == null) {
            transactionId = UUID.randomUUID().toString();
        }

        MDC.put("transactionId", transactionId);
        LOGGER.info("[REPORT SERVICE] Transaction Started - [{} {}] - Transaction ID: {}", request.getMethod(), request.getRequestURI(), transactionId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("[REPORT SERVICE] Transaction End - [{} {}] - Status: {} - Transaction ID: {}", request.getMethod(), request.getRequestURI(), response.getStatus(), MDC.get("transactionId"));
        MDC.clear();
    }
}
