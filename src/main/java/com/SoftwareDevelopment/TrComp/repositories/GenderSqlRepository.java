package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class GenderSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public GenderSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Gender MapGender(ResultSet rs) throws SQLException {
        Gender i = new Gender();
        i.setName(rs.getString("gender_name"));
        return i;

    }

    private RowMapper<Gender> MAPPER = new RowMapper<Gender>() {

        @Override
        public Gender mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gender i = MapGender(rs);
            return i;
        }
    };

    public Iterable<Gender> findAll(){
        String sql = "CALL GetAllGenders()";
        return jdbcTemplate.query(sql, MAPPER);
    }

    public Gender findById(String id){
        String sql = String.format("CALL GetGenderById('%s')", id);

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(String id) {
        String sql = String.format("CALL DeleteGenderById('%s')", id);
        jdbcTemplate.execute(sql);
    }

    public void save(Gender i) {

        String sql = String.format(
                "CALL SaveAndUpdateGender('%s')",
                i.getName());

        jdbcTemplate.execute(sql);
    }

};
