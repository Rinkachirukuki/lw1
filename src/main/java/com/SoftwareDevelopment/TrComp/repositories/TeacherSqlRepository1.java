/*
package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class TeacherSqlRepository1 {

    private JdbcTemplate jdbcTemplate;

    public TeacherSqlRepository1(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Teacher CMapper(ResultSet rs) throws SQLException {
        Teacher i = new Teacher();
        i.fName = rs.getString("first_name");
        i.lName = rs.getString("last_name");
        i.patronymic = rs.getString("patronymic");
        i.bDate = rs.getDate("birthday");
        i.email = rs.getString("email");
        i.pNumber = rs.getString("phone_number");
        return i;

    }

    private RowMapper<Teacher> MAPPER = new RowMapper<Teacher>() {
        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher i = CMapper(rs);
            return i;
        }
    };

    public Iterable<Teacher> findAll(){
        String sql = "SELECT * FROM teacher";



        return jdbcTemplate.query(sql, MAPPER);
    }

    public Teacher findById(String id){
        String sql = "SELECT * FROM teacher where teacher_code="+id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM teacher WHERE teacher_code=" + id;
        jdbcTemplate.execute(sql);
    }

    public void save(Teacher i) {
        String sql;

        String gender = i.getGender() == null ? "NULL" : "'" + i.getGender().getName() + "'";

        if (i.getId() == null){
            sql = String.format(
                    "INSERT INTO driver (first_name, last_name, patronymic, gender_name) VALUES ('%s', '%s','%s',%s)",
                    i.getFirstName(), i.getLastName(), i.getPatronymic(),
                    gender);
        }
        else {
            sql = String.format(
                    "UPDATE driver SET first_name='%s', last_name='%s', patronymic='%s', gender_name=%s\n"
                            + "WHERE driver_id='%d'",
                    i.getFirstName(), i.getLastName(), i.getPatronymic(),
                    gender,i.getId());
        }
        jdbcTemplate.execute(sql);
    }


}
*/