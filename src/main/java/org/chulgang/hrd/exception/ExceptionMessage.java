package org.chulgang.hrd.exception;

public class ExceptionMessage {
    public static final String CLOSING_RESULTSET_FAILED_EXCEPTION_MESSAGE = "ResultSet을 닫는 데 실패했습니다.";
    public static final String CLOSING_STATEMENT_FAILED_EXCEPTION_MESSAGE = "Statement를 닫는 데 실패했습니다.";
    public static final String DATE_TIME_PARSE_EXCEPTION_MESSAGE
            = "날짜 형식만 날짜 타입으로 변환할 수 있습니다. 현재 변환 대상 값: %s";

    public static final String LONG_PARSE_EXCEPTION_MESSAGE
            = "숫자값만 Long 타입으로 변환할 수 있습니다. 현재 변환 대상 값: %s";

    public static final String INTEGER_PARSE_EXCEPTION_MESSAGE
            = "숫자값만 int 타입으로 변환할 수 있습니다. 현재 변환 대상 값: %s";

    public static final String BYTE_PARSE_EXCEPTION_MESSAGE
            = "숫자값만 byte 타입으로 변환할 수 있습니다. 현재 변환 대상 값: %s";

    public static final String FLOAT_PARSE_EXCEPTION_MESSAGE
            = "소수값만 float 타입으로 변환할 수 있습니다. 현재 변환 대상 값: %s";

    public static final String BLOB_PARSE_EXCEPTION_MESSAGE
            = "파일 변환 중에 예외가 발생했습니다.";

    public static final String ARGUMENT_NO_VALUE_EXCEPTION_MESSAGE
            = "입력값이 전송되지 않았습니다. 다시 시도해 주세요.";

    public static final String JSON_SERIALIZATION_FAILED_EXCEPTION_MESSAGE
            = "JSON 직렬화에 실패했습니다. 다시 시도해 주세요. 대상 문자열: %s";
}
