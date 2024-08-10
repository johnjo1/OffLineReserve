package org.chulgang.hrd.course.exception;

import org.chulgang.hrd.exception.EntityNotFoundException;

public class CourseIdNotFoundException extends EntityNotFoundException {
    public CourseIdNotFoundException(String message) {
        super(message);
    }
}
