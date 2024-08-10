package org.chulgang.hrd.classroom.exception;

import org.chulgang.hrd.exception.EntityNotFoundException;

public class TimePeriodIdNotFoundException extends EntityNotFoundException {
    public TimePeriodIdNotFoundException(String message) {
        super(message);
    }
}
