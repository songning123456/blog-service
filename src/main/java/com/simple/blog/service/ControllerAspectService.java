package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author sn
 */
public interface ControllerAspectService {
    void controllerAspect();

    void doBefore(JoinPoint joinPoint) throws Throwable;

    <T> CommonDTO<T> doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    void doAfter() throws Throwable;
}
