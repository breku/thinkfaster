package com.kcal.aspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.kcal.annotation.Loggable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * User: Breku
 * Date: 2014-09-22
 */
@Component
@Configurable

@Aspect
public class LoggerAspect {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@annotation(com.kcal.annotation.Loggable)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        String level = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Loggable.class).level();
        boolean executionTime = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Loggable.class).executionTime();
        boolean arguments = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(Loggable.class).arguments();

        logMessage(point, ">> {}#{}({})", Level.toLevel(level), arguments, point.getArgs());
        Object result = point.proceed();
        logMessage(point, "<< {}#{}({})", Level.toLevel(level), arguments, new Object[]{result});

        if (executionTime) {
            logExecutionTime(start, point, Level.toLevel(level));
        }

        return result;
    }

    private void logExecutionTime(long startTime, ProceedingJoinPoint point, Level level) {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object[] arguments = new Object[]{className, methodName, System.currentTimeMillis() - startTime};
        String message = "<< {}#{} in time {}ms";
        logMessage(message, level, arguments);
    }

    private void logMessage(ProceedingJoinPoint point, String message, Level level, boolean includeArguments, Object[] arguments) {
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = point.getSignature().getName();

        Object[] allArguments;
        if (includeArguments) {
            allArguments = new Object[2 + arguments.length];
            allArguments[0] = className;
            allArguments[1] = methodName;
            for (int i = 0; i < arguments.length; i++) {
                allArguments[i + 2] = arguments[i];
            }
        } else {
            allArguments = new Object[3];
            allArguments[0] = className;
            allArguments[1] = methodName;
            allArguments[2] = "hidden";
        }
        logMessage(message, level, allArguments);
    }

    private void logMessage(String mesage, Level level, Object[] arguments) {
        if (level.equals(Level.DEBUG)) {
            LOGGER.debug(mesage, arguments);
        } else if (level.equals(Level.INFO)) {
            LOGGER.info(mesage, arguments);
        } else if (level.equals(Level.WARN)) {
            LOGGER.warn(mesage, arguments);
        } else if (level.equals(Level.ERROR)) {
            LOGGER.error(mesage, arguments);
        } else if (level.equals(Level.TRACE)) {
            LOGGER.trace(mesage, arguments);
        }


    }


}
