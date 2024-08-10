package org.chulgang.hrd.course.model.repository;

import org.chulgang.hrd.course.domain.Course;

import java.util.List;

public interface CourseRepository {
    int computePageCount(int size);

    List<Course> findAll(int size, int pageNumber);

    Course findById(Long id);

    boolean save(Course course);

    boolean existsByName(String courseName);

    int findRemainedSeatById(Long id);

    void updateRemainedSeatById(Long id, int newRemainedSeat);

    int getPageCount(int size);
}
