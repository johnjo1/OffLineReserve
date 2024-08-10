package org.chulgang.hrd.course.model.testutil;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.chulgang.hrd.course.model.testutil.CourseTestConstant.*;

public class CourseTestDataProvider {
    public static Stream<Arguments> provideCourseData() {
        return Stream.of(
                Arguments.of(
                        COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1, NAME1,
                        DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1, AVERAGE_SCORE1, REMAINED_SEAT1
                ),
                Arguments.of(
                        COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2, NAME2,
                        DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2, AVERAGE_SCORE2, REMAINED_SEAT2
                ),
                Arguments.of(
                        COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3, NAME3,
                        DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3, AVERAGE_SCORE3, REMAINED_SEAT3
                )
        );
    }
}