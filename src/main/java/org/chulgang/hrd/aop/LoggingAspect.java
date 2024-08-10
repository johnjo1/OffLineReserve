package org.chulgang.hrd.aop;

import org.chulgang.hrd.util.PerformanceMeasurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

import static org.chulgang.hrd.util.LogConstant.PERFORMANCE_MEASUREMENT;

public class LoggingAspect implements InvocationHandler {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    private final Object target;

    private static final String JOB_START_MESSAGE
            = "Beginning to '{}.{}' task by {}: '{}'";
    private static final String JOB_END_MESSAGE
            = "'{}.{}' task was executed successfully by {}: '{}', ";

    public LoggingAspect(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = target.getClass().getSimpleName();

        StringBuilder parameterNames = new StringBuilder();
        StringBuilder parameterValues = new StringBuilder();
        if (args != null && args.length > 0) {
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < args.length; i++) {
                if (i > 0) {
                    parameterNames.append(", ");
                    parameterValues.append(", ");
                }
                parameterNames.append(parameters[i].getName());
                parameterValues.append(args[i]);
            }
        }

        log.info(JOB_START_MESSAGE, className, methodName, parameterNames, parameterValues);

        long beforeMemory = PerformanceMeasurer.computeUsedMemory(0);
        long startedAt = PerformanceMeasurer.computeElapsedTime(0);

        Object result = method.invoke(target, args);

        long elapsedTime = PerformanceMeasurer.computeElapsedTime(startedAt);
        long memoryUsage = PerformanceMeasurer.computeUsedMemory(beforeMemory);

        log.info(
                JOB_END_MESSAGE + PERFORMANCE_MEASUREMENT,
                className, methodName, parameterNames, parameterValues, elapsedTime, memoryUsage
        );

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> interfaceType, T target) {
        return (T) Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType},
                new LoggingAspect(target)
        );
    }
}
