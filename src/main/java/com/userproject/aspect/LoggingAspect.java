package com.userproject.aspect;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

@Aspect
@Component
@Log4j
public class LoggingAspect
{
    private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.userproject.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String returnType = methodSignature.getReturnType().getSimpleName();
        String[] parameters = methodSignature.getParameterNames();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        LOGGER.info("Working method: " +" " + className +"."+ methodName+ " parameters: " + Arrays.toString(parameters)+" return type: " + returnType + " :: " + stopWatch.getTotalTimeMillis() + " ms");//console
//        log.info("Working method: " +" " + className +"."+ methodName+ " parameters: " + Arrays.toString(parameters)+" return type: " + returnType + " :: " + stopWatch.getTotalTimeMillis() + " ms");//txt
        return result;
    }
}