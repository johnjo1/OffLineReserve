package org.chulgang.hrd.course.model.testutil;

import org.chulgang.hrd.util.FormatConverter;

import java.time.LocalDate;

public class CourseTestConstant {
    public static final Long COURSE_ID1 = 1L;
    public static final Long COURSE_ID2 = 2L;
    public static final Long COURSE_ID3 = 3L;

    public static final Long SUBJECT_ID1 = 1L;
    public static final Long SUBJECT_ID2 = 2L;
    public static final Long SUBJECT_ID3 = 3L;

    public static final String SUBJECT_NAME = "수학";

    public static final Long TEACHER_ID1 = 1L;
    public static final Long TEACHER_ID2 = 2L;
    public static final Long TEACHER_ID3 = 3L;

    public static final String TEACHER_NAME = "홍길동";

    public static final Long TIME_PERIOD_ID1 = 1L;
    public static final Long TIME_PERIOD_ID2 = 2L;
    public static final Long TIME_PERIOD_ID3 = 3L;

    public static final String NAME1 = "테스트 강의1";
    public static final String NAME2 = "테스트 강의2";
    public static final String NAME3 = "테스트 강의3";

    public static final String DESCRIPTION1 = "테스트 강의1 설명";
    public static final String DESCRIPTION2 = "테스트 강의2 설명";
    public static final String DESCRIPTION3 = "테스트 강의3 설명";

    public static final int PRICE1 = 10_000;
    public static final int PRICE2 = 20_000;
    public static final int PRICE3 = 30_000;

    public static final LocalDate START_DATE1 = LocalDate.of(2024, 1, 1);
    public static final LocalDate START_DATE2 = LocalDate.of(2024, 1, 2);
    public static final LocalDate START_DATE3 = LocalDate.of(2024, 1, 3);

    public static final LocalDate LAST_DATE1 = LocalDate.of(2024, 2, 1);
    public static final LocalDate LAST_DATE2 = LocalDate.of(2024, 2, 2);
    public static final LocalDate LAST_DATE3 = LocalDate.of(2024, 2, 3);

    public static final String PARSED_START_DATE1 = FormatConverter.parseToString(START_DATE1);
    public static final String PARSED_START_DATE2 = FormatConverter.parseToString(START_DATE2);
    public static final String PARSED_START_DATE3 = FormatConverter.parseToString(START_DATE3);

    public static final String PARSED_LAST_DATE1 = FormatConverter.parseToString(LAST_DATE1);
    public static final String PARSED_LAST_DATE2 = FormatConverter.parseToString(LAST_DATE2);
    public static final String PARSED_LAST_DATE3 = FormatConverter.parseToString(LAST_DATE3);

    public static final float AVERAGE_SCORE1 = 1.5f;
    public static final float AVERAGE_SCORE2 = 2.7f;
    public static final float AVERAGE_SCORE3 = 3.9f;

    public static final int REMAINED_SEAT1 = 10;
    public static final int REMAINED_SEAT2 = 20;
    public static final int REMAINED_SEAT3 = 30;

    public static final int PAGE_COUNT = 10;

    public static final int SIZE1 = 2;
    public static final int SIZE2 = 3;

    public static final int PAGE_NUMBER = 1;
}
