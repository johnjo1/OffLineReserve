package org.chulgang.hrd.course.model.repository;

import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.exception.CourseIdNotFoundException;
import org.chulgang.hrd.course.model.testutil.CourseTestObjectFactory;
import org.chulgang.hrd.util.ConnectionContainer;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.util.StatementGenerator;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.chulgang.hrd.course.exception.ExceptionMessage.COURSE_ID_NOT_FOUND_EXCEPTION_MESSAGE;
import static org.chulgang.hrd.course.model.testutil.CourseTestConstant.*;
import static org.chulgang.hrd.util.ConfigConstant.DB_PROPERTY_KEY;
import static org.chulgang.hrd.util.ConfigConstant.TEST_DB_PROPERTY;

class CourseRepositoryTest {
    private CourseRepository courseRepository = CourseRepositoryImpl.getInstance();

    @BeforeAll
    static void setUpBeforeAll() {
        System.setProperty(DB_PROPERTY_KEY, TEST_DB_PROPERTY);
    }

    @BeforeEach
    void setUp() {
        DbConnection.initialize();
        createCourseTable();
    }

    @AfterEach
    void tearDown() {
        dropCourseTable();
    }

    private static void createCourseTable() {
        String sql = "CREATE TABLE COURSE (" +
                "ID NUMBER(19,0) NOT NULL, " +
                "SUBJECT_ID NUMBER(19,0) NOT NULL, " +
                "TEACHER_ID NUMBER(19,0) NOT NULL, " +
                "TIME_PERIOD_ID NUMBER(19,0) NOT NULL, " +
                "NAME VARCHAR2(255) NOT NULL, " +
                "DESCRIPTION VARCHAR(4000) NULL, " +
                "PRICE NUMBER(10,0) NOT NULL, " +
                "START_DATE DATE NOT NULL, " +
                "LAST_DATE DATE NOT NULL, " +
                "AVERAGE_SCORE NUMBER(1,1) NULL DEFAULT 0.0, " +
                "REMAINED_SEAT NUMBER(10,0) NOT NULL, " +
                "CREATED_AT DATE NOT NULL, " +
                "MODIFIED_AT DATE NULL" +
                ");";

        Statement statement = StatementGenerator.generateStatement();

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionContainer.close(statement);
    }

    private void dropCourseTable() {
        String sql = "DROP TABLE COURSE;";
        Statement statement = StatementGenerator.generateStatement();

        try {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionContainer.close(statement);
    }

    @DisplayName("Singleton instance를 불러 올 수 있다.")
    @Test
    void getInstance() {
        // given, when
        CourseRepository courseRepository = CourseRepositoryImpl.getInstance();

        // then
        assertThat(courseRepository).isInstanceOf(CourseRepository.class);
    }

    @DisplayName("전체 페이지 수를 계산할 수 있다.")
    @Test
    void computePageCount() {
        // given
        Course course1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        Course course2 = CourseTestObjectFactory.createCourse(
                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
        );
        Course course3 = CourseTestObjectFactory.createCourse(
                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
        );

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);

        // when
        int pageCount1 = courseRepository.computePageCount(SIZE1);
        int pageCount2 = courseRepository.computePageCount(SIZE2);

        // then
        assertThat(pageCount1).isEqualTo(2);
        assertThat(pageCount2).isEqualTo(1);
    }

    @DisplayName("원하는 페이지 크기와 페이지 번호에 해당하는 강좌 목록을 내림차순으로 조회할 수 있다.")
    @Test
    void findAll() {
        // given
        Course course1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        Course course2 = CourseTestObjectFactory.createCourse(
                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
        );
        Course course3 = CourseTestObjectFactory.createCourse(
                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
        );

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);

        // when
        List<Course> courses1 = courseRepository.findAll(SIZE1, PAGE_NUMBER);
        List<Course> courses2 = courseRepository.findAll(SIZE2, PAGE_NUMBER);

        // then
        assertThat(courses1).hasSize(2)
                .extracting(
                        "id", "subjectId", "teacherId", "timePeriodId", "name",
                        "description", "price", "startDate", "lastDate"
                )
                .containsExactly(
                        tuple(
                                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
                        ),
                        tuple(
                                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
                        )
                );

        assertThat(courses2).hasSize(3)
                .extracting(
                        "id", "subjectId", "teacherId", "timePeriodId", "name",
                        "description", "price", "startDate", "lastDate"
                )
                .containsExactly(
                        tuple(
                                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
                        ),
                        tuple(
                                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
                        ),
                        tuple(
                                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
                        )
                );
    }

    @DisplayName("강좌 ID를 통해 해당하는 강좌를 조회할 수 있다.")
    @Test
    void findById() {
        // given
        Course createdCourse1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );

        Course createdCourse2 = CourseTestObjectFactory.createCourse(
                COURSE_ID2, SUBJECT_ID2, TEACHER_ID2, TIME_PERIOD_ID2,
                NAME2, DESCRIPTION2, PRICE2, START_DATE2, LAST_DATE2
        );

        Course createdCourse3 = CourseTestObjectFactory.createCourse(
                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
        );

        courseRepository.save(createdCourse1);
        courseRepository.save(createdCourse2);
        courseRepository.save(createdCourse3);

        // when
        Course foundCourse = courseRepository.findById(createdCourse2.getId());

        // then
        assertThat(foundCourse).isEqualTo(createdCourse2);
    }

    @DisplayName("존재하지 않는 강좌 ID를 통해 강좌를 조회하려 하면 CourseIdNotFoundException이 발생한다.")
    @Test
    void findByIdFromNonExistentCourse() {
        // given
        Course createdCourse1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );


        Course createdCourse2 = CourseTestObjectFactory.createCourse(
                COURSE_ID3, SUBJECT_ID3, TEACHER_ID3, TIME_PERIOD_ID3,
                NAME3, DESCRIPTION3, PRICE3, START_DATE3, LAST_DATE3
        );

        courseRepository.save(createdCourse1);
        courseRepository.save(createdCourse2);

        // when, then
        assertThatThrownBy(() -> courseRepository.findById(COURSE_ID2))
                .isInstanceOf(CourseIdNotFoundException.class)
                .hasMessage(String.format(COURSE_ID_NOT_FOUND_EXCEPTION_MESSAGE, COURSE_ID2));
    }

    @DisplayName("새로운 강좌 튜플을 저장할 수 있다.")
    @Test
    void save() {
        // given
        Course createdCourse = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );

        // when
        courseRepository.save(createdCourse);
        Course foundCourse = courseRepository.findById(createdCourse.getId());

        // then
        assertThat(foundCourse).isEqualTo(createdCourse);
    }

    @DisplayName("강좌명 중복 여부를 검증하고, 중복되는 경우 true를 반환할 수 있다.")
    @Test
    void existsByNameWhenTrue() {
        // given
        Course createdCourse1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        courseRepository.save(createdCourse1);

        // when
        boolean isDuplicateName = courseRepository.existsByName(NAME1);

        // then
        assertThat(isDuplicateName).isTrue();
    }

    @DisplayName("강좌명 중복 여부를 검증하고, 중복되지 않는 경우 false를 반환할 수 있다.")
    @Test
    void existsByNameWhenFalse() {
        // given
        Course createdCourse1 = CourseTestObjectFactory.createCourse(
                COURSE_ID1, SUBJECT_ID1, TEACHER_ID1, TIME_PERIOD_ID1,
                NAME1, DESCRIPTION1, PRICE1, START_DATE1, LAST_DATE1
        );
        courseRepository.save(createdCourse1);

        // when
        boolean isDuplicateName = courseRepository.existsByName(NAME2);

        // then
        assertThat(isDuplicateName).isFalse();
    }
}
