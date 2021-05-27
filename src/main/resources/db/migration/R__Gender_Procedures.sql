DROP PROCEDURE IF EXISTS DeleteGenderById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteGenderById`(
in id integer
)
BEGIN
	DELETE FROM gender WHERE gender.gender_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllGenders;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllGenders`()
BEGIN
	SELECT * FROM gender;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetGenderById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetGenderById`(
in id varchar(255)
)
BEGIN
	SELECT * FROM gender WHERE gender.gender_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS SaveAndUpdateGender;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SaveAndUpdateGender`(
in gender_name varchar(255)
)
BEGIN
	INSERT INTO gender (gender_name)
    VALUES (gender_name)
    ON DUPLICATE KEY UPDATE gender_name = gender_name;
END ;;
DELIMITER ;