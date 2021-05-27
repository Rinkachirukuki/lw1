package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.*;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@org.springframework.transaction.annotation.Transactional
public class AchievementSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public AchievementSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private Achievement mapAchievement(ResultSet rs) throws SQLException {
        Achievement a = new Achievement();

        a.setId(rs.getInt("achievement_id"));
        a.setName(rs.getString("achievement_name"));
        a.setDescription(rs.getString("description"));

        return a;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        Customer c = new Customer();

        Gender g = new Gender();
        Privilege p = new Privilege();

        g.setName(rs.getString("gender_name"));
        p.setName(rs.getString("privilege_name"));

        c.setGender(g);
        c.setPrivilege(p);

        c.setId(rs.getInt("customer_fk"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setPatronymic(rs.getString("patronymic"));

        return c;
    }

    private ResultSetExtractor<List<Achievement>> MAPPER = new ResultSetExtractor<>() {

        @Override
        public List<Achievement> extractData(ResultSet rs) throws SQLException, DataAccessException {

            List<Achievement> achievementList = new ArrayList<>();
            Achievement achievement = null;

            while (rs.next()) {
                int id = rs.getInt("achievement_id");

                if (achievement == null) {
                    achievement = mapAchievement(rs);
                } else if (id != achievement.getId()) {
                    achievementList.add(achievement);
                    achievement = mapAchievement(rs);
                }
                if (rs.getString("customer_fk") != null) {
                    Customer customer = mapCustomer(rs);
                    achievement.AddCustomer(customer);
                }
            }
            if (achievement != null) {
                achievementList.add(achievement);
            }

            return achievementList;
        }
    };

    public Iterable<Achievement> findAll(){
        String sql =
                "SELECT achievement.achievement_id, achievement.description, achievement.achievement_name,\n" +
                "customer_achievement.customer_fk,\n" +
                "customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name FROM achievement\n" +
                "LEFT JOIN customer_achievement on customer_achievement.achievement_fk = achievement.achievement_id\n" +
                "LEFT JOIN customer on customer_achievement.customer_fk = customer.customer_id\n" +
                "ORDER BY achievement.achievement_id";
        return jdbcTemplate.query(sql, MAPPER);
    }

    public Achievement findById(int id){
        String sql = String.format(
                "SELECT achievement.achievement_id, achievement.description, achievement.achievement_name,\n" +
                "customer_achievement.customer_fk,\n" +
                "customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name FROM achievement\n" +
                "LEFT JOIN customer_achievement on customer_achievement.achievement_fk = achievement.achievement_id\n" +
                "LEFT JOIN customer on customer_achievement.customer_fk = customer.customer_id\n" +
                "WHERE achievement.achievement_id = '%d'\n" +
                "ORDER BY customer_achievement.achievement_fk", id);

        return jdbcTemplate.query(sql, MAPPER).get(0);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM achievement WHERE achievement_id=" + id;
        jdbcTemplate.execute(sql);
    }

    public void save(Achievement i) {

        Integer id = i.getId();

        String sql;

        if(id == null){
            sql = String
                    .format("INSERT INTO achievement (`description`, `achievement_name`)\n" +
                            " VALUES ('%s', '%s')", i.getDescription(), i.getName());
        }
        else {
            sql = String
                    .format("UPDATE achievement SET `description` = '%s', `achievement_name` = '%s'\n" +
                            "WHERE (`achievement_id` = '%d');", i.getDescription(), i.getName(), id);
        }

        jdbcTemplate.execute(sql);

        Set<Customer> customerSet = i.getCustomers();

        if (i.getId()!= null){
            String insertSql = "INSERT INTO customer_achievement (`customer_fk`, `achievement_fk`) VALUES ('%d', '%s') ON DUPLICATE KEY UPDATE customer_fk = customer_fk";

            deleteAllAchievementRelationships(id);

            for (Customer c: customerSet) {
                jdbcTemplate.execute(String.format(insertSql, c.getId(), id));
            }
        }

    }
    private void deleteAllAchievementRelationships(int id) {
        String sql = "DELETE FROM customer_achievement WHERE achievement_fk=" + id;
        jdbcTemplate.execute(sql);
    }

};
