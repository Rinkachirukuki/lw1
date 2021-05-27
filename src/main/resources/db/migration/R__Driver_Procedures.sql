DROP PROCEDURE IF EXISTS CreateNewDriver;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateNewDriver`(
IN f_name varchar(255),
IN l_name varchar(255),
IN patr varchar(255),
IN gend varchar(255)
)
BEGIN
    INSERT INTO driver (first_name, last_name, patronymic, gender_name) VALUES (f_name, l_name, patr, gend);
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteDriverById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteDriverById`(
in id integer
)
BEGIN
	DELETE FROM driver WHERE driver.driver_id=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllDrivers;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllDrivers`()
BEGIN
	SELECT * FROM driver;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetDriverById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetDriverById`(
in id integer
)
BEGIN
	SELECT * FROM driver where driver.driver_id= id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateDriver;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateDriver`(
IN id integer,
IN f_name varchar(255),
IN l_name varchar(255),
IN patr varchar(255),
IN gend varchar(255)
)
BEGIN
    UPDATE driver SET first_name = f_name, last_name = l_name, patronymic = patr, gender_name = gend WHERE driver.driver_id = id;
END ;;
DELIMITER ;