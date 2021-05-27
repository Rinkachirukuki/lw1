DROP PROCEDURE IF EXISTS CreateNewVehicle;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateNewVehicle`(
IN v_model varchar(255),
IN v_number varchar(255),
IN v_dr_id integer,
IN mark_n varchar(255)
)
BEGIN
        INSERT INTO vehicle (model, number, driver_id, mark_name) VALUES (v_model, v_number, v_dr_id, mark_n);
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteVehicleById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteVehicleById`(
in id integer
)
BEGIN
	DELETE FROM vehicle WHERE vehicle.vehicle_id=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllVehicles;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllVehicles`()
BEGIN
	SELECT vehicle.vehicle_id, vehicle.model , vehicle.number ,vehicle.driver_id, vehicle.mark_name,
    driver.first_name, driver.last_name, driver.patronymic
    FROM vehicle
    LEFT JOIN driver ON driver.driver_id = vehicle.driver_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetVehicleById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetVehicleById`(
in id integer
)
BEGIN
	SELECT vehicle.vehicle_id, vehicle.model , vehicle.number ,vehicle.driver_id, vehicle.mark_name,
    driver.first_name, driver.last_name, driver.patronymic
    FROM vehicle
    LEFT JOIN driver ON driver.driver_id = vehicle.driver_id
    WHERE vehicle.vehicle_id= id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateVehicle;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateVehicle`(
in v_id integer,
IN v_model varchar(255),
IN v_number varchar(255),
IN v_dr_id integer,
IN mark_n varchar(255)
)
BEGIN
    UPDATE vehicle SET model=v_model, number=v_number, driver_id= v_dr_id, mark_name= mark_n WHERE vehicle.vehicle_id= v_id;
END ;;
DELIMITER ;