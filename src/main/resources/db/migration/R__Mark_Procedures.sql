DROP PROCEDURE IF EXISTS DeleteMarkById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteMarkById`(
in id varchar(255)
)
BEGIN
    DELETE FROM mark WHERE mark.mark_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllMarks;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllMarks`()
BEGIN
    SELECT * FROM mark;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS GetMarkById;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetMarkById`(
in id varchar(255)
)
BEGIN
    SELECT * FROM mark WHERE mark.mark_name=id;
END ;;
DELIMITER ;

DROP PROCEDURE IF EXISTS SaveAndUpdateMark;

DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SaveAndUpdateMark`(
in mark_n varchar(255)
)
BEGIN
	INSERT INTO mark (mark_name)
    VALUES (mark_n)
    ON DUPLICATE KEY UPDATE mark_name = mark_name;
END ;;
DELIMITER ;
