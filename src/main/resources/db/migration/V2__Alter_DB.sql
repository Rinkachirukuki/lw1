
-- LabWork 2

ALTER TABLE `achievement`
    RENAME COLUMN `name` TO `achievement_name`;

ALTER TABLE `achievement`
      ADD `description` varchar(255) DEFAULT NULL
        AFTER `achievement_id`;

ALTER TABLE `driver`
      DROP COLUMN `middle_name`;

-- Insert

INSERT INTO `achievement` VALUES (1,'У вас получилось','Доехать живым'),(2,'Не заплатив за дорогу','Убежать от таксиста');

INSERT INTO `gender` VALUES ('Гуманоид'),('Женщина'),('Мужчина');

INSERT INTO `mark` VALUES ('Audi'),('Ford'),('Lada');

INSERT INTO `privilege` VALUES ('Basic','Обычный покупатель',0),('Gold','Постоянный покупатель',20),('Platinum','Постоянный покупатель +',30),('Silver','Частый покупатель',10);