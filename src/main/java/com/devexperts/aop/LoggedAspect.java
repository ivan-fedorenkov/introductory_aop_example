package com.devexperts.aop;

import com.devexperts.service.CrudServiceBase;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.StringJoiner;

/**
 * @author ifedorenkov
 */
@Aspect
public class LoggedAspect {

    @Pointcut("com.devexperts.aop.Pointcuts.withinServicePackage() && target(service) && @annotation(logged)")
    public void logged(CrudServiceBase<?, ?> service, Logged logged) {}

    @Before(value = "logged(service, logged)", argNames = "joinPoint,service,logged")
    public void before(JoinPoint joinPoint, CrudServiceBase<?, ?> service, Logged logged) {
        if (isLogSupported(service, logged.level())) {
            StringBuilder msgBuilder = new StringBuilder();
            appendTarget(msgBuilder, joinPoint);
            appendParams(msgBuilder, joinPoint.getArgs());
            log(service, logged.level(), msgBuilder.toString());
        }
    }

    @AfterReturning(pointcut = "logged(service, logged)", returning = "result", argNames = "joinPoint,result,service,logged")
    public void afterReturning(JoinPoint joinPoint, Object result, CrudServiceBase<?, ?> service, Logged logged) {
        if (isLogSupported(service, logged.level())) {
            StringBuilder msgBuilder = new StringBuilder();
            appendTarget(msgBuilder, joinPoint);
            appendResult(msgBuilder, result);
            log(service, logged.level(), msgBuilder.toString());
        }
    }

    @AfterThrowing(pointcut = "logged(service, logged)", argNames = "joinPoint,service,logged")
    public void afterThrowing(JoinPoint joinPoint, CrudServiceBase<?, ?> service, Logged logged) {
        if (isLogSupported(service, LogLevel.ERROR)) {
            StringBuilder msgBuilder = new StringBuilder();
            appendTarget(msgBuilder, joinPoint);
            msgBuilder.append("=error");
            log(service, LogLevel.ERROR, msgBuilder.toString());
        }
    }

    private static void appendTarget(StringBuilder builder, JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        builder.append(signature.getDeclaringTypeName()).append("#").append(signature.getName());
    }

    private static void appendParams(StringBuilder builder, Object... params) {
        builder.append("[");
        if (params.length > 0) {
            StringJoiner paramsJoiner = new StringJoiner(",");
            for (Object param : params)
                paramsJoiner.add(param.toString());
            builder.append(paramsJoiner.toString());
        }
        builder.append("]");
    }

    private static void appendResult(StringBuilder builder, Object result) {
        if (result == null) {
            builder.append("=ok");
        } else {
            builder.append("=").append(result.toString());
        }
    }
    
    private static boolean isLogSupported(CrudServiceBase<?, ?> service, LogLevel logLevel) {
        switch (logLevel) {
            case DEBUG:
                return service.isDebugEnabled();
            case INFO:
                return service.isInfoEnabled();
            case ERROR:
                return service.isErrorEnabled();
            default:
                return false;
        }
    }
    
    private static void log(CrudServiceBase<?, ?> service, LogLevel logLevel, String msg) {
        switch (logLevel) {
            case DEBUG:
                service.debug(msg);
                break;
            case INFO:
                service.info(msg);
                break;
            case ERROR:
                service.error(msg);
                break;
        }
    }

}
