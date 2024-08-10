package org.chulgang.hrd.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public static void throwCheckedException(Exception exception) {
        try {
            throw exception;
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }

    public static void throwRuntimeException(RuntimeException runtimeException) {
        log.info(runtimeException.getMessage());
        throw runtimeException;
    }
}
