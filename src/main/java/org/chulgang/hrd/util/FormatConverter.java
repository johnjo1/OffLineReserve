package org.chulgang.hrd.util;

import org.chulgang.hrd.exception.AllDateTimeParseFailedException;
import org.chulgang.hrd.exception.BlobParseFailedException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import static org.chulgang.hrd.exception.ExceptionMessage.*;

public class FormatConverter {
    private static final List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS")
    );

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime parseToDateTime(String dateTime) {
        StringBuilder parsedData = new StringBuilder();
        int errorIndex = 0;

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException dtpe) {
                parsedData.append(dtpe.getParsedString());
                parsedData.append(",\n");
                errorIndex = dtpe.getErrorIndex();
            }
        }
        parsedData.delete(parsedData.length() - 2, parsedData.length());
        GlobalExceptionHandler.throwRuntimeException(
                new AllDateTimeParseFailedException(
                        String.format(DATE_TIME_PARSE_EXCEPTION_MESSAGE, dateTime),
                        parsedData.toString(),
                        errorIndex
                )
        );

        return null;
    }

    public static LocalDate parseToDate(String date) {
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException dtpe) {
            return parseToDateWithoutTime(date);
        }
    }

    private static LocalDate parseToDateWithoutTime(String date) {
        try {
            return LocalDate.parse(date.substring(0, 10), dateFormatter);
        } catch (DateTimeParseException dtpe) {
            GlobalExceptionHandler.throwRuntimeException
                    (new DateTimeParseException(
                            String.format(DATE_TIME_PARSE_EXCEPTION_MESSAGE, date),
                            dtpe.getParsedString(), dtpe.getErrorIndex()));
        }

        return null;
    }

    public static String parseToString(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatters.get(0));
    }

    public static String parseToString(LocalDate date) {
        return date.format(dateFormatter);
    }

    public static long parseToLong(String number) {
        try {
            return Long.parseLong(number);
        } catch (NumberFormatException nfe) {
            GlobalExceptionHandler.throwRuntimeException(
                    new NumberFormatException(String.format(LONG_PARSE_EXCEPTION_MESSAGE, number))
            );
        }

        return -1;
    }

    public static int parseToInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            GlobalExceptionHandler.throwRuntimeException(
                    new NumberFormatException(String.format(INTEGER_PARSE_EXCEPTION_MESSAGE, number))
            );
        }

        return -1;
    }

    public static byte parseToByte(String number) {
        try {
            return Byte.parseByte(number);
        } catch (NumberFormatException nfe) {
            GlobalExceptionHandler.throwRuntimeException(
                    new NumberFormatException(String.format(BYTE_PARSE_EXCEPTION_MESSAGE, number))
            );
        }

        return -1;
    }

    public static float parseToFloat(String prime) {
        try {
            return Float.parseFloat(prime);
        } catch (NumberFormatException nfe) {
            GlobalExceptionHandler.throwRuntimeException(
                    new NumberFormatException(String.format(FLOAT_PARSE_EXCEPTION_MESSAGE, prime))
            );
        }

        return -1;
    }

    public static boolean parseToBoolean(byte number) {
        if (number == 0) {
            return false;
        }

        return true;
    }

    public static Blob parseToBlob(String str) {
        try {
            return new SerialBlob(str.getBytes());
        } catch (SQLException se) {
            GlobalExceptionHandler.throwCheckedException(new BlobParseFailedException((BLOB_PARSE_EXCEPTION_MESSAGE)));
        }

        return null;
    }
}
