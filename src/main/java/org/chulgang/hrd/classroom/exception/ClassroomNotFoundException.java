package org.chulgang.hrd.classroom.exception;

import org.chulgang.hrd.exception.EntityNotFoundException;

public class ClassroomNotFoundException extends EntityNotFoundException {
    public ClassroomNotFoundException(String message) {
        super(message);
    }
}
