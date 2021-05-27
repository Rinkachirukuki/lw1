DROP PROCEDURE IF EXISTS CreateNewAchievement;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `CreateNewAchievement`(
IN achievement_name varchar(255),
IN achievement_description varchar(255)
)
BEGIN
	INSERT INTO achievement (`description`, `achievement_name`) VALUES (achievement_description, achievement_name);
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteAchievementById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteAchievementById`(
IN ach_id integer
)
BEGIN
	DELETE FROM achievement WHERE achievement_id= ach_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteAllAchievementRelationships;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteAllAchievementRelationships`(
IN id integer
)
BEGIN
	DELETE FROM customer_achievement WHERE customer_achievement.achievement_fk=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAchievementById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAchievementById`(
IN ach_id integer
)
BEGIN
	SELECT achievement.achievement_id, achievement.description, achievement.achievement_name,
    customer_achievement.customer_fk,
    customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name FROM achievement
    LEFT JOIN customer_achievement on customer_achievement.achievement_fk = achievement.achievement_id
    LEFT JOIN customer on customer_achievement.customer_fk = customer.customer_id
    WHERE achievement.achievement_id = ach_id
    ORDER BY customer.customer_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllAchievements;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllAchievements`()
BEGIN
	SELECT achievement.achievement_id, achievement.description, achievement.achievement_name,
    customer_achievement.customer_fk,
    customer.first_name, customer.last_name, customer.patronymic, customer.gender_name, customer.privilege_name FROM achievement
    LEFT JOIN customer_achievement on customer_achievement.achievement_fk = achievement.achievement_id
    LEFT JOIN customer on customer_achievement.customer_fk = customer.customer_id
    ORDER BY achievement.achievement_id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS SetCustomerAchievement;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SetCustomerAchievement`(
IN customer_id integer,
IN achievement_id integer

)
BEGIN
    INSERT INTO customer_achievement (`customer_fk`, `achievement_fk`) VALUES (customer_id, achievement_id) ON DUPLICATE KEY UPDATE customer_fk = customer_fk;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateAchievement;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateAchievement`(
IN achievement_id integer,
IN achievement_description varchar(255),
IN achievement_name varchar(255)
)
BEGIN
	UPDATE `achievement` SET `description` = achievement_description, `achievement_name` = achievement_name
    WHERE (achievement.achievement_id = achievement_id);
END ;;
DELIMITER ;