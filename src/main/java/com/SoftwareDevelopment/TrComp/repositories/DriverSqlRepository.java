package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Gender;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@org.springframework.transaction.annotation.Transactional
public class DriverSqlRepository  {

    private JdbcTemplate jdbcTemplate;

    public DriverSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Driver MapDriver(ResultSet rs) throws SQLException {
        Driver i = new Driver();
        Gender g = new Gender();
        g.setName(rs.getString("gender_name"));
        i.setId(rs.getInt("driver_id"));
        i.setFirstName(rs.getString("first_name"));
        i.setLastName(rs.getString("last_name"));
        i.setPatronymic(rs.getString("patronymic"));
        i.setGender(g);
        return i;

    }

    private RowMapper<Driver> MAPPER = new RowMapper<Driver>() {

        @Override
        public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
            Driver i = MapDriver(rs);
            return i;
        }
    };

    public Iterable<Driver> findAll(){
        String sql = "SELECT * FROM driver";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Driver findById(int id){
        String sql = "SELECT * FROM driver where driver_id=" + id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM driver WHERE driver_id=" + id;
        jdbcTemplate.execute(sql);
    }

    public void save(Driver i) {
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
