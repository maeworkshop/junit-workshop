package com.maemresen.tcw.sb.remote.docker.aspects;

import com.maemresen.tcw.sb.remote.docker.exceptions.InvalidParameterException;
import com.maemresen.tcw.sb.remote.docker.exceptions.ServiceException;
import com.maemresen.tcw.sb.remote.docker.exceptions.UnexpectedException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class BusinessMethodAspect {

    @Pointcut("@annotation(com.maemresen.tcw.sb.remote.docker.aspects.annotations.BusinessMethod)")
    public void businessMethod() {
        // This pointcut expression targets all methods with @BusinessMethod
    }

    @AfterThrowing(pointcut = "businessMethod()", throwing = "throwable")
    public void afterThrowingException(Throwable throwable) throws Throwable {
        if (throwable instanceof ServiceException) {
            throw throwable;
        }

        if (throwable instanceof ConstraintViolationException constraintViolationException) {
            throw new InvalidParameterException(constraintViolationException.getMessage(), constraintViolationException);
        }

        log.warn("{} exception is not allowed to thrown by service, Converting into ServiceException", throwable.getClass());
        throw new UnexpectedException(throwable);
    }
}


