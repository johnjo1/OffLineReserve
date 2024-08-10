package org.chulgang.hrd.course.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {
    private static final String COURSE_DATA_PATH
            = "org.chulgang.hrd.course.model.testutil.CourseTestDataProvider#provideCourseData";

    @DisplayName("올바른 값을 전송하면 강좌 인스턴스를 생성할 수 있다.")
    @ParameterizedTest
    @MethodSource(COURSE_DATA_PATH)
    void from(Long id, Long subjectId, Long teacherId, Long timePeriodId, String name, String description,
              int price, LocalDate startDate, LocalDate lastDate, float averageScore, int remainedSeat) {
        // given
        LocalDateTime now = LocalDateTime.now();
        String[] data = {
                String.valueOf(id), String.valueOf(subjectId), String.valueOf(teacherId),
                String.valueOf(timePeriodId), name, description, String.valueOf(price), String.valueOf(startDate),
                String.valueOf(lastDate), String.valueOf(averageScore), String.valueOf(remainedSeat),
                String.valueOf(now), String.valueOf(now)
        };

        // when
        Course course = Course.from(data);

        // then
        assertThat(course.getId()).isEqualTo(id);
        assertThat(course.getSubjectId()).isEqualTo(subjectId);
        assertThat(course.getTeacherId()).isEqualTo(teacherId);
        assertThat(course.getTimePeriodId()).isEqualTo(timePeriodId);
        assertThat(course.getName()).isEqualTo(name);
        assertThat(course.getDescription()).isEqualTo(description);
        assertThat(course.getPrice()).isEqualTo(price);
        assertThat(course.getStartDate()).isEqualTo(startDate);
        assertThat(course.getLastDate()).isEqualTo(lastDate);
        assertThat(course.getAverageScore()).isEqualTo(averageScore);
        assertThat(course.getRemainedSeat()).isEqualTo(remainedSeat);
        assertThat(course.getCreatedAt()).isEqualTo(now);
        assertThat(course.getModifiedAt()).isEqualTo(now);
    }

    @DisplayName("동일한 내부 값들을 전송하면 동등한 강좌 인스턴스를 생성한다.")
    @ParameterizedTest
    @MethodSource(COURSE_DATA_PATH)
    void fromSameValue(Long id, Long subjectId, Long teacherId, Long timePeriodId, String name, String description,
                       int price, LocalDate startDate, LocalDate lastDate, float averageScore, int remainedSeat) {
        // given
        LocalDateTime now = LocalDateTime.now();
        String[] data = {
                String.valueOf(id), String.valueOf(subjectId), String.valueOf(teacherId),
                String.valueOf(timePeriodId), name, description, String.valueOf(price), String.valueOf(startDate),
                String.valueOf(lastDate), String.valueOf(averageScore), String.valueOf(remainedSeat),
                String.valueOf(now), String.valueOf(now)
        };

        // when
        Course course1 = Course.from(data);
        Course course2 = Course.from(data);

        // then
        assertThat(course1).isEqualTo(course2);
        assertThat(course1.hashCode()).isEqualTo(course2.hashCode());
    }

    @DisplayName("다른 내부 값들을 전송하면 동등하지 않은 Course 인스턴스를 생성한다.")
    @ParameterizedTest
    @MethodSource(COURSE_DATA_PATH)
    void fromDifferentValue(Long id, Long subjectId, Long teacherId, Long timePeriodId, String name, String description,
                            int price, LocalDate startDate, LocalDate lastDate, float averageScore, int remainedSeat) {
        // given
        LocalDateTime now = LocalDateTime.now();
        String[] data1 = {
                String.valueOf(id), String.valueOf(subjectId), String.valueOf(teacherId),
                String.valueOf(timePeriodId), name, description, String.valueOf(price), String.valueOf(startDate),
                String.valueOf(lastDate), String.valueOf(averageScore), String.valueOf(remainedSeat),
                String.valueOf(now), String.valueOf(now.plusNanos(1))
        };

        String[] data2 = {
                String.valueOf(id), String.valueOf(subjectId), String.valueOf(teacherId),
                String.valueOf(timePeriodId), name, description, String.valueOf(price), String.valueOf(startDate),
                String.valueOf(lastDate), String.valueOf(averageScore), String.valueOf(remainedSeat),
                String.valueOf(now), String.valueOf(now)
        };

        // when
        Course course1 = Course.from(data1);
        Course course2 = Course.from(data2);

        // then
        assertThat(course1).isNotEqualTo(course2);
        assertThat(course1.hashCode()).isNotEqualTo(course2.hashCode());
    }
}
