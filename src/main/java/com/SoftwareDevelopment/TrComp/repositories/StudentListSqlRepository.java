package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.StudentList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class StudentListSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public StudentListSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private StudentList CMapper(ResultSet rs) throws SQLException {
        StudentList i = new StudentList();
        i.studentListId = rs.getInt("student_list_id");
        i.status = rs.getString("status");
        i.academicClass.classId = rs.getInt("class_id");
        i.student.studentCode = rs.getString("student_code");
        return i;

    }

    private RowMapper<StudentList> MAPPER = new RowMapper<StudentList>() {
        @Override
        public StudentList mapRow(ResultSet rs, int rowNum) throws SQLException {
            StudentList i = CMapper(rs);
            return i;
        }
    };

    public Iterable<StudentList> findAll(){
        String sql = "SELECT * FROM student_list";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public StudentList findById(Integer id){
        String sql = "SELECT * FROM student_list where student_list_id="+id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }
}
