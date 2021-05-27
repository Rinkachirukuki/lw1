DROP PROCEDURE IF EXISTS DeletePrivilegeById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletePrivilegeById`(
in id varchar(255)
)
BEGIN
	DELETE FROM privilege WHERE privilege.privilege_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllPrivileges;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllPrivileges`()
BEGIN
	SELECT * FROM privilege;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetPrivilegeById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPrivilegeById`(
in id varchar(255)
)
BEGIN
	SELECT * FROM privilege WHERE privilege.privilege_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS SaveAndUpdatePrivilege;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SaveAndUpdatePrivilege`(
in pr_name varchar(255),
in pr_desc varchar(255),
in disc_s integer
)
BEGIN
	INSERT INTO privilege (privilege_name, description, discount_size) VALUES (pr_name,pr_desc,disc_s )
    ON DUPLICATE KEY UPDATE description = pr_desc, discount_size = disc_s;
END ;;
DELIMITER ;
