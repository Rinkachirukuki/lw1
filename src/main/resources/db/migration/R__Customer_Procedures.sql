DROP PROCEDURE IF EXISTS CreateNewCustomer;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateNewCustomer`(
IN f_name varchar(255),
IN l_name varchar(255),
IN patr varchar(255),
IN gend varchar(255),
IN pr_name varchar(255)
)
BEGIN
    INSERT INTO customer (`first_name`, `last_name`, `patronymic`, `gender_name`, `privilege_name`) VALUES (f_name, l_name, patr, gend, pr_name);
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteAllCustomerRelationships;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteAllCustomerRelationships`(
IN id integer
)
BEGIN
    DELETE FROM customer_achievement WHERE customer_achievement.customer_fk=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteCustomerById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteCustomerById`(
IN id integer
)
BEGIN
    DELETE FROM customer WHERE customer.customer_id = id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllCustomers;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllCustomers`()
BEGIN
	SELECT customer.customer_id, customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name,
    achievement.achievement_id, achievement.description, achievement.achievement_name
    FROM customer
    LEFT JOIN customer_achievement on customer_achievement.customer_fk = customer.customer_id
    LEFT JOIN achievement on customer_achievement.achievement_fk = achievement.achievement_id
    ORDER BY customer.customer_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetCustomerById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCustomerById`(
IN cust_id integer
)
BEGIN
    SELECT customer.customer_id, customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name,
    achievement.achievement_id, achievement.description, achievement.achievement_name
    FROM customer
    LEFT JOIN customer_achievement on customer_achievement.customer_fk = customer.customer_id
    LEFT JOIN achievement on customer_achievement.achievement_fk = achievement.achievement_id
    WHERE customer.customer_id = cust_id
    ORDER BY achievement.achievement_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateCustomer;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateCustomer`(
IN id integer,
IN f_name varchar(255),
IN l_name varchar(255),
IN patr varchar(255),
IN gend varchar(255),
IN pr_name varchar(255)
)
BEGIN
    UPDATE `customer` SET `first_name` = f_name , `last_name` = l_name, `patronymic` = patr, `gender_name` = gend, `privilege_name` = pr_name
    WHERE (customer.customer_id = id);
END ;;
DELIMITER ;



