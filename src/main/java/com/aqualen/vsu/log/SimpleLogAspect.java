package com.aqualen.vsu.log;

import com.aqualen.vsu.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aspect
@Component
@Slf4j
public class SimpleLogAspect {

    @Around("@annotation(com.aqualen.vsu.log.SimpleLog)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String template = "user: %s, %s with parameters (%s): completed in %s";
        String userName = Optional.ofNullable(UserUtils.getUsername()).orElse("Unknown");
        String signature = joinPoint.getSignature().toShortString();
        String values = getParametersValues(joinPoint);

        long start = new Date().getTime();
        Object proceed = joinPoint.proceed();
        log.info(String.format(template, userName, signature, values, + (new Date().getTime() - start) + " ms"));

        return proceed;
    }

    private static String getParametersValues(ProceedingJoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();

        if (arguments == null || arguments.length == 0) {
            return "no parameters";
        }

        return Stream.of(arguments).map(o -> o == null ? "null" : o.toString())
                .collect(Collectors.joining(", "));
    }
}
