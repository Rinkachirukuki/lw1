package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Achievement;
import com.SoftwareDevelopment.TrComp.models.Customer;
import com.SoftwareDevelopment.TrComp.models.Gender;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@org.springframework.transaction.annotation.Transactional
public class CustomerSqlRepository {

    private JdbcTemplate jdbcTemplate;

    public CustomerSqlRepository(JdbcTemplate jdbcTemplate) {
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

        c.setId(rs.getInt("customer_id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setPatronymic(rs.getString("patronymic"));

        return c;
    }

    private ResultSetExtractor<List<Customer>> MAPPER = new ResultSetExtractor<>() {

        @Override
        public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {

            List<Customer> customerList = new ArrayList<>();
            Customer customer = null;

            while (rs.next()) {
                int id = rs.getInt("customer_id");

                if (customer == null) {
                    customer = mapCustomer(rs);
                } else if (id != customer.getId()) {
                    customerList.add(customer);
                    customer = mapCustomer(rs);
                }
                if (rs.getString("achievement_id") != null) {
                    Achievement achievement = mapAchievement(rs);
                    customer.getAchievements().add(achievement);
                }
            }
            if (customer != null) {
                customerList.add(customer);
            }

            return customerList;
        }
    };

    public Iterable<Customer> findAll(){
        String sql = "CALL GetAllCustomers()";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Customer findById(int id){
        String sql = String.format("CALL GetCustomerById('%d')", id);

        return jdbcTemplate.query(sql, MAPPER).get(0);
    }

    public void deleteById(int id) {
        String sql = String.format("CALL DeleteCustomerById('%d')", id);
        jdbcTemplate.execute(sql);
    }

    public void save(Customer i) {

        Integer id = i.getId();

        String gender = i.getGender() == null ? "NULL" : "'" + i.getGender().getName() + "'";
        String privilege = i.getPrivilege() == null ? "NULL" : "'" + i.getPrivilege().getName() + "'";

        String sql;

        if(id == null){
            sql = String.format("CALL CreateNewCustomer('%s', '%s', '%s', %s, %s)",
                            i.getFirstName(), i.getLastName(),i.getPatronymic(),gender,privilege);
        }
        else {
            sql = String.format("CALL UpdateCustomer('%d', '%s', '%s', '%s', %s, %s)",
                            id, i.getFirstName(), i.getLastName(),i.getPatronymic(),gender,privilege);
        }

        jdbcTemplate.execute(sql);

        Set<Achievement> achievementSet = i.getAchievements();

        if (i.getId()!= null){
            String insertSql = "CALL SetCustomerAchievement('%s', '%s')";

            deleteAllCustomerRelationships(id);

            for (Achievement c: achievementSet) {
                jdbcTemplate.execute(String.format(insertSql, id, c.getId()));
            }
        }
    }
    private void deleteAllCustomerRelationships(int id) {
        String sql = String.format("CALL DeleteAllCustomerRelationships('%s')", id);
        jdbcTemplate.execute(sql);
    }



};
