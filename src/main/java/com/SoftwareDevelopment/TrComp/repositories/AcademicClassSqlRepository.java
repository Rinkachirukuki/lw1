package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.AcademicClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private RowMapper<AcademicClass> MAPPER = new RowMapper<AcademicClass>() {
        @Override
        public AcademicClass mapRow(ResultSet rs, int rowNum) throws SQLException {
            AcademicClass i = CMapper(rs);
            return i;
        }
    };

    public Iterable<AcademicClass> findAll(){
        String sql = "SELECT * FROM academic_class";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public AcademicClass findById(Integer id){
        String sql = "SELECT * FROM academic_class where class_id="+id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }
}
