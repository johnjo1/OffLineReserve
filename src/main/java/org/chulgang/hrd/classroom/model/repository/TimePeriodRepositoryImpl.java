package org.chulgang.hrd.classroom.model.repository;

import org.chulgang.hrd.classroom.domain.TimePeriod;
import org.chulgang.hrd.classroom.dto.UpdateUsedTimePeriodRequest;
import org.chulgang.hrd.classroom.exception.TimePeriodIdNotFoundException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.util.ConnectionContainer;
import org.chulgang.hrd.util.DataSelector;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.util.StatementGenerator;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.chulgang.hrd.classroom.exception.ExceptionMessage.TIME_PERIOD_ID_NOT_FOUND_EXCEPTION_MESSAGE;

public class TimePeriodRepositoryImpl implements TimePeriodRepository {
    private static final TimePeriodRepository INSTANCE = new TimePeriodRepositoryImpl();

    public static TimePeriodRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TimePeriod> findUsableTimesByClassroomId(Long classroomId, LocalDate startDate, LocalDate lastDate) {
        String sql
                = "select * from TIME_PERIOD"
                + " where CLASSROOM_ID = ?"
                + " and (IS_USED = 0 or START_DATE > ? or LAST_DATE < ?)";

        PreparedStatement preparedStatement = StatementGenerator.generateStatement(sql);
        ResultSet resultSet = null;
        try {
            preparedStatement.setLong(1, classroomId);
            preparedStatement.setDate(2, Date.valueOf(lastDate));
            preparedStatement.setDate(3, Date.valueOf(startDate));
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        List<TimePeriod> timePeriods = new ArrayList<>();

        while (true) {
            String[] data = DataSelector.getEntityData(resultSet);
            if (data == null) {
                break;
            }
            timePeriods.add(TimePeriod.from(data));
        }

        ConnectionContainer.close(resultSet);
        ConnectionContainer.close(preparedStatement);
        DbConnection.reset();

        return timePeriods;
    }

    @Override
    public TimePeriod findById(Long id) {
        String sql = String.format("select * from TIME_PERIOD where ID = %d", id);

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        String[] data = DataSelector.getEntityData(resultSet);
        if (data == null) {
            GlobalExceptionHandler.throwRuntimeException(
                    new TimePeriodIdNotFoundException(String.format(TIME_PERIOD_ID_NOT_FOUND_EXCEPTION_MESSAGE, id))
            );
        }

        ConnectionContainer.close(resultSet);
        ConnectionContainer.close(statement);
        DbConnection.reset();

        return TimePeriod.from(data);
    }

    @Override
    public void updateUsedTimePeriodById(UpdateUsedTimePeriodRequest updateUsedTimePeriodRequest) {
        String sql = "update TIME_PERIOD set START_DATE = ?, LAST_DATE = ?, IS_USED = 1 WHERE ID = ?";
        Long id = updateUsedTimePeriodRequest.getId();

        PreparedStatement preparedStatement = StatementGenerator.generateStatement(sql);

        try {
            preparedStatement.setObject(1, updateUsedTimePeriodRequest.getStartDate());
            preparedStatement.setObject(2, updateUsedTimePeriodRequest.getLastDate());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            GlobalExceptionHandler.throwRuntimeException(
                    new TimePeriodIdNotFoundException(String.format(TIME_PERIOD_ID_NOT_FOUND_EXCEPTION_MESSAGE, id))
            );
        }

        ConnectionContainer.close(preparedStatement);
        DbConnection.reset();
    }
}
