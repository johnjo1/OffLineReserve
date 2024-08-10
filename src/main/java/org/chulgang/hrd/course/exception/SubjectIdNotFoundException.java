package org.chulgang.hrd.course.exception;

import org.chulgang.hrd.exception.EntityNotFoundException;

public class SubjectIdNotFoundException extends EntityNotFoundException {
    public SubjectIdNotFoundException(String message) {
        super(message);
    }
}
