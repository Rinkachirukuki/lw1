package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Audience;
import com.SoftwareDevelopment.TrComp.models.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class DriverSqlRepository  {

    private JdbcTemplate jdbcTemplate;
/*
    public DriverSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Teacher MapDriver(ResultSet rs) throws SQLException {
        Teacher i = new Teacher();
        Audience g = new Audience();
        g.setName(rs.getString("gender_name"));
        i.setId(rs.getInt("driver_id"));
        i.setFirstName(rs.getString("first_name"));
        i.setLastName(rs.getString("last_name"));
        i.setPatronymic(rs.getString("patronymic"));
        i.setGender(g);
        return i;

    }

    private RowMapper<Teacher> MAPPER = new RowMapper<Teacher>() {

        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher i = MapDriver(rs);
            return i;
        }
    };

    public Iterable<Teacher> findAll(){
        String sql = "SELECT * FROM driver";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Teacher findById(int id){
        String sql = "SELECT * FROM driver where driver_id=" + id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM driver WHERE driver_id=" + id;
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
*/

}
