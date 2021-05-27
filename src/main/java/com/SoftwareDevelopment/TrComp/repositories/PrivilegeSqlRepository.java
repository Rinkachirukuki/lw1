package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class PrivilegeSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public PrivilegeSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Privilege MapPrivilege(ResultSet rs) throws SQLException {
        Privilege i = new Privilege();
        i.setName(rs.getString("privilege_name"));
        i.setDescription(rs.getString("description"));
        i.setDiscountSize(rs.getInt("discount_size"));

        return i;
    }

    private RowMapper<Privilege> MAPPER = new RowMapper<Privilege>() {

        @Override
        public Privilege mapRow(ResultSet rs, int rowNum) throws SQLException {
            Privilege i = MapPrivilege(rs);
            return i;
        }
    };

    public Iterable<Privilege> findAll(){
        String sql = "CALL GetAllPrivileges()";
        return jdbcTemplate.query(sql, MAPPER);
    }

    public Privilege findById(String id){
        String sql = String.format("CALL GetPrivilegesById('%s')", id);
        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(String id) {
        String sql = String.format("CALL DeletePrivilegesById('%s')", id);
        jdbcTemplate.execute(sql);
    }

    public void save(Privilege i) {

        String description = i.getDescription();
        int discountSize = i.getDiscountSize();

        String sql = String.format(
                "CALL SaveAndUpdatePrivilege('%s', '%s', '%d')",
                        i.getName(),description,discountSize);

        jdbcTemplate.execute(sql);
    }

}
