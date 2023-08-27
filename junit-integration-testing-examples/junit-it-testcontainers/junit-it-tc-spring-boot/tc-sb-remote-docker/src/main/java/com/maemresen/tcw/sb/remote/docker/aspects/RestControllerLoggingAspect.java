package com.maemresen.tcw.sb.remote.docker.aspects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Aspect
@Component
@Slf4j
public class RestControllerLoggingAspect {

    private static final String X_FORWARDED_FOR_HEADER = "X-FORWARDED-FOR";

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restControllerMethods() {
    }

    @AfterThrowing(pointcut = "restControllerMethods()", throwing = "exception")
    public void logExceptions(Throwable exception) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = Optional.ofNullable(request.getHeader(X_FORWARDED_FOR_HEADER))
            .orElseGet(request::getRemoteAddr);
        String uri = request.getRequestURI();
        String httpMethod = request.getMethod();
        log.error("Exception occurred while processing request from IP {} using {} {}: {}",
                ipAddress,
                httpMethod,
                uri,
                exception.getMessage(),
                exception);
    }
}