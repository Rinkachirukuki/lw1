package com.SoftwareDevelopment.TrComp.repositories;

import com.SoftwareDevelopment.TrComp.models.Gender;
import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.models.Driver;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SoftwareDevelopment.TrComp.models.Vehicle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@org.springframework.transaction.annotation.Transactional
public class VehicleSqlRepository {
    private JdbcTemplate jdbcTemplate;

    public VehicleSqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Vehicle MapVehicle(ResultSet rs) throws SQLException {
        Vehicle i = new Vehicle();

        Mark m = new Mark();
        Driver d = new Driver();

        m.setName(rs.getString("mark_name"));

        d.setId(rs.getInt("driver_id"));
        d.setFirstName(rs.getString("first_name"));
        d.setLastName(rs.getString("last_name"));
        d.setPatronymic(rs.getString("patronymic"));

        i.setId(rs.getInt("vehicle_id"));
        i.setModel(rs.getString("model"));
        i.setNumber(rs.getString("number"));

        i.setMark(m);
        i.setDriver(d);

        return i;
    }

    private RowMapper<Vehicle> MAPPER = new RowMapper<Vehicle>() {

        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle i = MapVehicle(rs);
            return i;
        }
    };

    public Iterable<Vehicle> findAll(){
        String sql =
                "SELECT vehicle.vehicle_id, vehicle.model , vehicle.number ,vehicle.driver_id, vehicle.mark_name,\n" +
                "driver.first_name, driver.last_name, driver.patronymic\n" +
                "FROM vehicle\n" +
                "LEFT JOIN driver ON driver.driver_id = vehicle.driver_id";

        return jdbcTemplate.query(sql, MAPPER);
    }

    public Vehicle findById(int id){
        String sql =
                "SELECT vehicle.vehicle_id, vehicle.model , vehicle.number ,vehicle.driver_id, vehicle.mark_name,\n" +
                "driver.first_name, driver.last_name, driver.patronymic\n" +
                "FROM vehicle\n" +
                "LEFT JOIN driver ON driver.driver_id = vehicle.driver_id\n"+
                "WHERE vehicle.vehicle_id="+ id;

        return jdbcTemplate.queryForObject(sql, MAPPER);
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM vehicle WHERE vehicle_id=" + id;
        jdbcTemplate.execute(sql);
    }

    public void save(Vehicle i) {
        String sql;

        String mark = i.getMark() == null ? "NULL" : "'" + i.getMark().getName() + "'";
        String driver = i.getDriver().getId() == null ? "NULL" : "'" + i.getDriver().getId() + "'";

        if (i.getId() == null){
            sql = String.format(
                    "INSERT INTO vehicle (model, number, driver_id, mark_name) VALUES ('%s', '%s',%s,%s)",
                    i.getModel(), i.getNumber(), driver,
                    mark);
        }
        else {
            sql = String.format(
                    "UPDATE vehicle SET model='%s', number='%s', driver_id=%s, mark_name=%s "
                            + "WHERE vehicle_id='%d'",
                    i.getModel(), i.getNumber(), driver,
                    mark,i.getId());
        }
        jdbcTemplate.execute(sql);
    }
}
