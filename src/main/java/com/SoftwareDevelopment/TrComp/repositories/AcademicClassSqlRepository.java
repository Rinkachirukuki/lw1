package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import com.SoftwareDevelopment.TrComp.models.answers.AcademicGroupStatistics;
import com.SoftwareDevelopment.TrComp.models.answers.AllAcademicGroupStatistics;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
@org.springframework.transaction.annotation.Transactional
public class AcademicClassSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public AcademicClassSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private AcademicClass CMapper(ResultSet rs) throws SQLException {
        AcademicClass i = new AcademicClass();
        i.classId = rs.getInt("class_id");
        i.classDate = rs.getTimestamp("class_date").toLocalDateTime();
        i.academicGroup.groupCode = rs.getString("group_code");
        i.academicDiscipline.disciplineCode = rs.getString("discipline_code");
        i.teacher.teacherCode = rs.getString("teacher_code");
        i.audience.audienceCode = rs.getString("audience_code");
        return i;

    }

    private AllAcademicGroupStatistics AGAllStatisticsMapper(ResultSet rs) throws SQLException {
        AllAcademicGroupStatistics i = new AllAcademicGroupStatistics();

        i.groupCode = rs.getString("group_code");
        i.studentCount = rs.getInt("student_count");
        i.classCount = rs.getInt("class_count");
        i.classVisits = rs.getInt("class_visits");
        i.reasonableMisses = rs.getInt("reasonable_misses_count");
        i.visitPercentage = rs.getFloat("visit_percentage");

        return i;
    }

    private AcademicGroupStatistics AGStatisticsMapper(ResultSet rs) throws SQLException {
        AcademicGroupStatistics i = new AcademicGroupStatistics();

        i.student.studentCode = rs.getString("student_code");
        i.student.firstName = rs.getString("first_name");
        i.student.lastName = rs.getString("last_name");
        i.student.patronymic = rs.getString("patronymic");
        i.student.birthDate = rs.getDate("birth_date").toLocalDate();
        i.student.email = rs.getString("email");
        i.student.phoneNumber = rs.getString("phone_number");

        i.classVisits = rs.getInt("class_visits");
        i.visitPercentage = rs.getFloat("visit_percentage");
        i.reasonableMissesPercentage = rs.getInt("reasonable_miss_percentage");

        return i;

    }

    private RowMapper<AcademicClass> MAPPER = new RowMapper<AcademicClass>() {
        @Override
        public AcademicClass mapRow(ResultSet rs, int rowNum) throws SQLException {
            AcademicClass i = CMapper(rs);
            return i;
        }
    };

    private RowMapper<AllAcademicGroupStatistics> MapperAllStats = new RowMapper<AllAcademicGroupStatistics>() {
        @Override
        public AllAcademicGroupStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
            AllAcademicGroupStatistics i = AGAllStatisticsMapper(rs);
            return i;
        }
    };

    private RowMapper<AcademicGroupStatistics> MapperStats = new RowMapper<AcademicGroupStatistics>() {
        @Override
        public AcademicGroupStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
            AcademicGroupStatistics i = AGStatisticsMapper(rs);
            return i;
        }
    };

    public Iterable<AllAcademicGroupStatistics> findAllAcademicGroupStatistics(Date startDate, Date endDate){
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM get_all_groups_statistics(?,?)");
                preparedStatement.setDate(1,startDate);
                preparedStatement.setDate(2,endDate);
                return preparedStatement;
            }
        }, MapperAllStats);
    }

    public Iterable<AcademicGroupStatistics> findAcademicGroupStatistics(String group, Date startDate, Date endDate){
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM get_group_statistics(?,?,?)");
                preparedStatement.setString(1,group);
                preparedStatement.setDate(2,startDate);
                preparedStatement.setDate(3,endDate);
                return preparedStatement;
            }
        }, MapperStats);
    }

    public Iterable<AcademicClass> findAll(){
        String sql = "SELECT * FROM academic_class";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Iterable<AcademicClass> findById(Integer id){
        return jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM academic_class where class_id=?");
                preparedStatement.setInt(1,id);
                return preparedStatement;
            }
        }, MAPPER);
    }
}
