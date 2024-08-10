package org.chulgang.hrd.course.util;

public class RequestConstant {
    public static final String COURSE_DOMAIN_NAME = "course";
    public static final String CLASSROOM_DOMAIN_NAME = "classroom";

    public static final String COURSE_SERVICE_ATTRIBUTE_NAME = "courseService";
    public static final String SUBJECT_SERVICE_ATTRIBUTE_NAME = "subjectService";
    public static final String CLASSROOM_SERVICE_ATTRIBUTE_NAME = "classroomService";
    public static final String TIME_PERIOD_SERVICE_ATTRIBUTE_NAME = "timePeriodService";
    public static final String USER_SERVICE_ATTRIBUTE_NAME = "usersService";

    public static final String RESERVATION_SERVICE_ATTRIBUTE_NAME = "reservationService";
    public static final String PAYMENT_SERVICE_ATTRIBUTE_NAME = "paymentService";
    public static final String WALLET_HISTORY_SERVICE_ATTRIBUTE_NAME = "walletHistoryService";

    public static final String GET_SUBJECTS_ATTRIBUTE_NAME = "getSubjectsResponse";
    public static final String GET_CLASSROOMS_ATTRIBUTE_NAME = "getClassroomsResponse";
    public static final String LOGIN_SESSION_ATTRIBUTE_NAME = "dto";

    public static final String GET_COURSES_FIRST_REQUEST_URL = "/elearn/course/courses.do";
    public static final String GET_COURSES_SECOND_REQUEST_URL = "/elearn/courses.do";
    public static final String COURSES_ATTRIBUTE_NAME = "courses";
    public static final String SEARCH_WORD_PARAMETER_NAME = "searchWord";
    public static final String SIZE_PARAMETER_NAME = "size";
    public static final String PAGE_COUNT_PARAMETER_NAME = "pageCount";
    public static final String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";
    public static final String COURSE_NAME_PARAMETER_NAME = "courseName";
    public static final String CLASSROOM_ID_PARAMETER_NAME = "classroomId";
    public static final String START_DATE_PARAMETER_NAME = "start-date";
    public static final String LAST_DATE_PARAMETER_NAME = "last-date";

    public static final String COURSES_VIEW = "courses-grid.jsp";
    public static final String LOGIN_FAILED_VIEW = "login-failed.jsp";
    public static final String AUTHORIZATION_FAILED_VIEW = "authorization-failed.jsp";
    public static final String COURSE_REGISTRATION_VIEW = "course-registration-form.jsp";
    public static final String COURSE_REGISTRATION_CONFIRM_VIEW = "course-registration-confirm.jsp";

    public static final String GET_COURSE_FIRST_REQUEST_URL = "/elearn/course/course-details.do";
    public static final String GET_COURSE_SECOND_REQUEST_URL = "/elearn/course-details.do";
    public static final String ID_PARAMETER_NAME = "id";
    public static final String TEACHER_ROLE_NAME = "teacher";
    public static final String COURSE_DETAIL_VIEW = "course-details.jsp";

    public static final String REGISTER_COURSE_FIRST_REQUEST_URL = "/elearn/course/registration.do";
    public static final String REGISTER_COURSE_SECOND_REQUEST_URL = "/elearn/registration.do";
    public static final String VALIDATION_URL = "/elearn/course/validation.do";
    public static final String JSON_CONTENT_TYPE = "application/json;charset=utf-8";

    public static final String GET_PERIODS_FIRST_REQUEST_URL = "/elearn/classroom/get-periods.do";
    public static final String GET_PERIODS_SECOND_REQUEST_URL = "/elearn/get-periods.do";
}
