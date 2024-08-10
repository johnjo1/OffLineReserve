package org.chulgang.hrd.course.model.repository;

import org.chulgang.hrd.course.domain.Subject;
import org.chulgang.hrd.course.exception.SubjectIdNotFoundException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;
import org.chulgang.hrd.util.ConnectionContainer;
import org.chulgang.hrd.util.DataSelector;
import org.chulgang.hrd.util.DbConnection;
import org.chulgang.hrd.util.StatementGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.chulgang.hrd.course.exception.ExceptionMessage.SUBJECT_ID_NOT_FOUND_EXCEPTION_MESSAGE;

public class SubjectRepositoryImpl implements SubjectRepository {
    private static final SubjectRepository INSTANCE = new SubjectRepositoryImpl();

    public static SubjectRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Subject> findAll() {
        String sql = "select * from SUBJECT";

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);
        List<Subject> subjects = new ArrayList<>();

        while (true) {
            String[] data = DataSelector.getEntityData(resultSet);
            if (data == null) {
                break;
            }

            subjects.add(Subject.from(data));
        }

        ConnectionContainer.close(resultSet);
        ConnectionContainer.close(statement);
        DbConnection.reset();

        return subjects;
    }

    @Override
    public String findNameById(Long id) {
        String sql = "select NAME from SUBJECT where id = " + id;

        Statement statement = StatementGenerator.generateStatement();
        ResultSet resultSet = DataSelector.getResultSet(statement, sql);

        try {
            if (resultSet.next()) {
                String name = resultSet.getString(1);
                ConnectionContainer.close(resultSet);
                ConnectionContainer.close(statement);
                return name;
            }
        } catch (SQLException se) {
            GlobalExceptionHandler.throwRuntimeException(
                    new SubjectIdNotFoundException(String.format(SUBJECT_ID_NOT_FOUND_EXCEPTION_MESSAGE, id)));
        } finally {
            DbConnection.reset();
        }

        return null;
    }
}
