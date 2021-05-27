package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Gender;
import com.SoftwareDevelopment.TrComp.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class MarkSqlRepository{

    private JdbcTemplate jdbcTemplate;

    public MarkSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Mark MapMark(ResultSet rs) throws SQLException {
        Mark i = new Mark();
        i.setName(rs.getString("mark_name"));
        return i;

    }

    private RowMapper<Mark> MAPPER = new RowMapper<Mark>() {

        @Override
        public Mark mapRow(ResultSet rs, int rowNum) throws SQLException {
            Mark i = MapMark(rs);
            return i;
        }
    };

    public Iterable<Mark> findAll(){
        String sql = "SELECT * FROM mark";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Mark findById(String id){
        String sql = "SELECT * FROM mark WHERE mark_name=" + id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM mark WHERE mark_name=" + id;
        jdbcTemplate.execute(sql);
    }

    public void save(Mark i) {

        String sql = String.format(
                "INSERT INTO mark (mark_name) VALUES ('%s') ON DUPLICATE KEY UPDATE mark_name = mark_name",
                i.getName());

        jdbcTemplate.execute(sql);
    }
};