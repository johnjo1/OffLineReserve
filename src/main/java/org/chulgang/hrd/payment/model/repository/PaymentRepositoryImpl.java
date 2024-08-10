package org.chulgang.hrd.payment.model.repository;

import org.chulgang.hrd.payment.domain.PayedCourse;
import org.chulgang.hrd.payment.dto.PaymentCardResponse;
import org.chulgang.hrd.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {
    private static final PaymentRepositoryImpl INSTANCE = new PaymentRepositoryImpl();

    public static PaymentRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<PaymentCardResponse> findPaymentCourseCardByMemberId(Long userId, int pageNumber) {
        List<PaymentCardResponse> payments = new ArrayList<>();
        int pageSize = 10;
        int offset = (pageNumber - 1) * pageSize;

        try {
            Connection connection = DbConnection.getConnection();
            String sql =
                    "SELECT pc.ID, c.NAME, c.DESCRIPTION, pc.PAYED_AMOUNT, pc.IS_REFUNDED, c.START_DATE, c.LAST_DATE " +
                    "FROM PAYED_COURSE pc " +
                    "JOIN COURSE c ON pc.COURSE_ID = c.ID " +
                    "JOIN RESERVATION r ON pc.RESERVATION_ID = r.ID " +
                    "WHERE r.STUDENT_ID = ? " +
                    "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PaymentCardResponse payment = new PaymentCardResponse();
                payment.setPayedCourseId(resultSet.getLong("ID"));
                payment.setCourseName(resultSet.getString("NAME"));
                payment.setCourseDescription(resultSet.getString("DESCRIPTION"));
                payment.setPayedAmount(resultSet.getInt("PAYED_AMOUNT"));
                payment.setRefunded(resultSet.getBoolean("IS_REFUNDED"));
                payment.setStartDate(resultSet.getDate("START_DATE").toLocalDate());
                payment.setEndDate(resultSet.getDate("LAST_DATE").toLocalDate());
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }

        return payments;
    }

    @Override
    public Optional<PayedCourse> findPayedCourseById(Long payedCourseId) {
        String selectPayedCourse = "SELECT * FROM PAYED_COURSE WHERE ID = ?";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(selectPayedCourse);
            preparedStatement.setLong(1, payedCourseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PayedCourse payedCourse = new PayedCourse();
                payedCourse.setId(resultSet.getLong("ID"));
                payedCourse.setCourseId(resultSet.getLong("COURSE_ID"));
                payedCourse.setReservationId(resultSet.getLong("RESERVATION_ID"));
                payedCourse.setModifiedAt(resultSet.getTimestamp("MODIFIED_AT") != null ? resultSet.getTimestamp("MODIFIED_AT").toLocalDateTime() : null);
                payedCourse.setPayedAt(resultSet.getTimestamp("PAYED_AT") != null ? resultSet.getTimestamp("PAYED_AT").toLocalDateTime() : null);
                payedCourse.setPayedAmount(resultSet.getInt("PAYED_AMOUNT"));
                payedCourse.setRefunded(resultSet.getBoolean("IS_REFUNDED"));
                payedCourse.setRefundedAt(resultSet.getTimestamp("REFUNDED_AT") != null ? resultSet.getTimestamp("REFUNDED_AT").toLocalDateTime() : null);
                payedCourse.setExpired(resultSet.getBoolean("IS_EXPIRED"));
                return Optional.of(payedCourse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return Optional.empty();
    }

    @Override
    public int countPayments(Long userId) {
        String sql =
                "SELECT COUNT(*) " +
                "FROM RESERVATION r " +
                "JOIN PAYED_COURSE pc ON r.ID = pc.RESERVATION_ID " +
                "WHERE r.STUDENT_ID = " + userId;
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
        return 0;
    }

    @Override
    public void insertPayedCourse(Long userId, Long reservationId, Long courseId, int paymentAmount) {
        String sql = "INSERT INTO PAYED_COURSE (ID, COURSE_ID, RESERVATION_ID, PAYED_AT, PAYED_AMOUNT, IS_REFUNDED, IS_EXPIRED) " +
                "VALUES (PAYED_COURSE_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, 0, 0)";
        try {Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, reservationId);
            preparedStatement.setInt(3, paymentAmount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
    }

    @Override
    public void updatePayedCourseRefundStatus(Long payedCourseId, boolean isRefunded) {
        String sql = "UPDATE PAYED_COURSE SET IS_REFUNDED = ? WHERE ID = ?";
        try {Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, isRefunded);
            preparedStatement.setLong(2, payedCourseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbConnection.reset();
        }
    }
}