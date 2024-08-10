package org.chulgang.hrd.course.model.repository;

import org.chulgang.hrd.course.domain.Course;
import org.chulgang.hrd.course.exception.CourseIdNotFoundException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.util.ConnectionContainer;
import org.chulgang.hrd.util.DataSelector;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.util.StatementGenerator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.chulgang.hrd.course.exception.ExceptionMessage.COURSE_ID_NOT_FOUND_EXCEPTION_MESSAGE;

public class CourseRepositoryImpl implements CourseRepository {
    private static final CourseRepository INSTANCE = new CourseRepositoryImpl();

    public static CourseRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public int computePageCount(int size) {
        String sql = "select count(ID) from COURSE";
        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        try {
            if (resultSet.next()) {
                int totalSize = resultSet.getInt(1);

                if (totalSize % size == 0) {
                    return totalSize / size;
                }

                return totalSize / size + 1;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            DbConnection.reset();
        }

        return -1;
    }

    @Override
    public List<Course> findAll(int size, int pageNumber) {
        int offset = size * (pageNumber - 1);
        String sql = String.format("SELECT * FROM (SELECT c.*, ROW_NUMBER() " +
                "OVER (ORDER BY ID DESC) AS rnum FROM COURSE c) "
                + "WHERE rnum BETWEEN %d AND %d order by ID desc", offset + 1, offset + size);

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);
        List<Course> courses = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String[] data = DataSelector.getEntityData(resultSet);
            if (data == null) {
                break;
            }

            courses.add(Course.from(data));
        }

        ConnectionContainer.close(resultSet);
        ConnectionContainer.close(statement);
        DbConnection.reset();

        return courses;
    }

    @Override
    public Course findById(Long id) {
        String sql = String.format("SELECT * FROM COURSE WHERE ID = %d", id);

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        String[] data = DataSelector.getEntityData(resultSet);
        if (data == null) {
            GlobalExceptionHandler.throwRuntimeException(
                    new CourseIdNotFoundException(String.format(COURSE_ID_NOT_FOUND_EXCEPTION_MESSAGE, id))
            );
        }

        ConnectionContainer.close(resultSet);
        ConnectionContainer.close(statement);
        DbConnection.reset();

        return Course.from(data);
    }

    @Override
    public boolean save(Course course) {
        String sql = "insert into COURSE(ID, SUBJECT_ID, TEACHER_ID, TIME_PERIOD_ID, NAME, DESCRIPTION, PRICE, "
                + "START_DATE, LAST_DATE, CREATED_AT, REMAINED_SEAT) "
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";

        if (course.isIdNull()) {
            sql = "insert into COURSE(ID, SUBJECT_ID, TEACHER_ID, TIME_PERIOD_ID, NAME, DESCRIPTION, PRICE, "
                    + "START_DATE, LAST_DATE, CREATED_AT, REMAINED_SEAT) "
                    + "values(COURSE_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?)";
        }

        PreparedStatement preparedStatement = StatementGenerator.generateStatement(sql);

        try {
            course.setTupleValues(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            return false;
        }

        ConnectionContainer.close(preparedStatement);
        DbConnection.reset();
        return true;
    }

    @Override
    public boolean existsByName(String courseName) {
        String sql = String.format("SELECT COUNT(ID) FROM COURSE WHERE NAME = '%s'", courseName);

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        try {
            if (resultSet.next()) {
                int idCount = resultSet.getInt(1);
                DbConnection.reset();
                return idCount > 0;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return true;
    }

    @Override
    public int findRemainedSeatById(Long id) {
        String sql = "select REMAINED_SEAT from COURSE where id = " + id;

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        try {
            if (resultSet.next()) {
                int remainedSeat = resultSet.getInt(1);
                ConnectionContainer.close(resultSet);
                ConnectionContainer.close(statement);
                DbConnection.reset();
                return remainedSeat;
            }
        } catch (SQLException se) {
            GlobalExceptionHandler.throwRuntimeException(
                    new CourseIdNotFoundException(String.format(COURSE_ID_NOT_FOUND_EXCEPTION_MESSAGE, id)));
        }

        return -1;
    }

    @Override
    public void updateRemainedSeatById(Long id, int newRemainedSeat) {
        String sql = "update COURSE set REMAINED_SEAT = ? where ID = ?";
        PreparedStatement preparedStatement = StatementGenerator.generateStatement(sql);

        try {
            preparedStatement.setInt(1, newRemainedSeat);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }

        ConnectionContainer.close(preparedStatement);
        DbConnection.reset();
    }

    @Override
    public int getPageCount(int size) {
        String sql = "select count(ID) from COURSE";

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        try {
            if (resultSet.next()) {
                int totalCount = resultSet.getInt(1);
                ConnectionContainer.close(resultSet);
                ConnectionContainer.close(statement);
                DbConnection.reset();
                return totalCount % size == 0 ? totalCount / size : totalCount / size + 1;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return -1;
    }
}
