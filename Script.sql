CREATE DATABASE  IF NOT EXISTS `climate` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `climate`;

CREATE TABLE `atmosfernae_yvleniy` (
  `id_osadky` int(11) NOT NULL AUTO_INCREMENT,
  `Vid_yvleniy` varchar(35) NOT NULL,
  `Picture` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_osadky`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

LOCK TABLES `atmosfernae_yvleniy` WRITE;
INSERT INTO `atmosfernae_yvleniy` VALUES (1,'Ясно_ночь','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Luna_ysno.png'),(2,'Ясно_день','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Solnce.png'),(3,'Малооблачно_день','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Malo_oblahno.png'),(4,'Малооблачно_ноч','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Malooblahno_noh.png'),(5,'Облачно_ноч','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Oblahno_veher.png'),(6,'Облачно_временами_дождь_день','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Oblahno_vremenamy_dogdy.png'),(7,'Облачно_небольшой_дождь_день','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Oblavno_nebol_dogde.png'),(8,'Пасмурно','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Pasmurno.png'),(9,'Пасмурно_дождь','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Pasmurno_dogdy.png'),(10,'Пасмурно_небольшой_дождь','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Pasmurno_nebolh_dogdy.png'),(11,'Пасмурно_сильный_дождь','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_атмосферн_явления\\Pasmurno_silny_dogdy.png');
UNLOCK TABLES;


CREATE TABLE `climat_poyas` (
  `id_poyas` int(11) NOT NULL AUTO_INCREMENT,
  `Name_climate_poyas` varchar(35) NOT NULL,
  `Opisanie` varchar(550) DEFAULT NULL,
  PRIMARY KEY (`id_poyas`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


LOCK TABLES `climat_poyas` WRITE;
INSERT INTO `climat_poyas` VALUES (1,'Экваториальный','Очень жарко'),(2,'Тропичский','Очень очень жарко'),(3,'Умеренный','Среднии температуры'),(4,'Арктический (нтарктический)','Очень очень холодно'),(5,'Субэкваториальный',NULL),(6,'Субтропический',NULL),(7,'Субарктический(субантарктический)',NULL);

CREATE TABLE `region` (
  `id_region` int(11) NOT NULL AUTO_INCREMENT,
  `Name_region` varchar(35) NOT NULL,
  `Opisanie` varchar(550) DEFAULT NULL,
  PRIMARY KEY (`id_region`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

LOCK TABLES `region` WRITE;
INSERT INTO `region` VALUES (1,'Северная америка','Канада, США, Мексика'),(2,'Центральная америка','Куба'),(3,'Южная америка','Бразилия'),(4,'Африка','ЮАР'),(5,'Европа','Сербия'),(6,'Евразия','Россия'),(7,'Азия','Китай, Япония, Индия'),(8,'Австралия','Австралия'),(9,'Антарктида','Антарктида');
UNLOCK TABLES;


CREATE TABLE `country` (
  `id_country` int(11) NOT NULL AUTO_INCREMENT,
  `Name_country` varchar(35) NOT NULL,
  `id_region` int(11) NOT NULL,
  PRIMARY KEY (`id_country`),
  KEY `id_region` (`id_region`),
  CONSTRAINT `country_ibfk_1` FOREIGN KEY (`id_region`) REFERENCES `region` (`id_region`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


LOCK TABLES `country` WRITE;
INSERT INTO `country` VALUES (1,'Россия',6),(2,'США',1),(3,'Куба',2),(4,'Бразилия',3),(5,'ЮАР',4),(6,'Сербия',5),(7,'Германия',5),(8,'Китай',7),(9,'Япония',7),(10,'Индия',7),(11,'Австралия',8),(12,'Антарктида',9);

DROP TABLE IF EXISTS `naselennay_punkt`;
CREATE TABLE `naselennay_punkt` (
  `id_punkt` int(11) NOT NULL AUTO_INCREMENT,
  `Name_naselen_punkt` varchar(30) DEFAULT NULL,
  `id_country` int(11) NOT NULL,
  `id_poyas` int(11) NOT NULL,
  PRIMARY KEY (`id_punkt`),
  KEY `id_poyas` (`id_poyas`),
  KEY `id_country` (`id_country`),
  CONSTRAINT `naselennay_punkt_ibfk_2` FOREIGN KEY (`id_poyas`) REFERENCES `climat_poyas` (`id_poyas`),
  CONSTRAINT `naselennay_punkt_ibfk_3` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


LOCK TABLES `naselennay_punkt` WRITE;
INSERT INTO `naselennay_punkt` VALUES (1,'Чикаго',2,3),(2,'Нью-Йорк',2,3),(3,'Москва',1,3),(4,'Новосибирск',1,4),(5,'Сочи',1,6),(6,'Екатеринбург',1,7),(7,'Гавана',3,1),(8,'Рио-де-Жанейро',4,5),(9,'Кейптаун',5,5),(10,'Белград',6,3),(11,'Берлин',7,3),(12,'Пекин',8,6),(13,'Токио',9,6),(14,'Нью-Дели',10,2),(15,'Канберра',11,2);

CREATE TABLE `veter` (
  `id_veter` int(11) NOT NULL AUTO_INCREMENT,
  `Napravlenie_vetra` varchar(21) NOT NULL,
  `Picture` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_veter`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


LOCK TABLES `veter` WRITE;
INSERT INTO `veter` VALUES (2,'С','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Северный.png'),(3,'Ю','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Южный.jpg'),(4,'В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(5,'З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(6,'С/В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Ю_З___С_В.png'),(7,'С/З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(8,'Ю/В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(9,'Ю/З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Ю_З___С_В.png');

DROP TABLE IF EXISTS `pogoda`;
CREATE TABLE `pogoda` (
  `id_pogoda` int(11) NOT NULL AUTO_INCREMENT,
  `id_punkt` int(11) NOT NULL,
  `Data_` date NOT NULL,
  `t_noh` int(11) DEFAULT NULL,
  `t_utro` int(11) DEFAULT NULL,
  `t_day` int(11) DEFAULT NULL,
  `t_veher` int(11) DEFAULT NULL,
  `Vlagnosty` int(11) DEFAULT NULL,
  `Davlenie` int(11) DEFAULT NULL,
  `id_osadky_noh` int(11) DEFAULT NULL,
  `id_osadky_utro` int(11) DEFAULT NULL,
  `id_osadky_day` int(11) DEFAULT NULL,
  `id_osadky_veher` int(11) DEFAULT NULL,
  `Skorosty_vetra` int(11) DEFAULT NULL,
  `Napravlenie_vetra` int(11) DEFAULT NULL,
  `Temperatura_voda` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_pogoda`),
  KEY `id_punkt` (`id_punkt`),
  KEY `id_osadky_noh` (`id_osadky_noh`),
  KEY `id_osadky_utro` (`id_osadky_utro`),
  KEY `id_osadky_day` (`id_osadky_day`),
  KEY `id_osadky_veher` (`id_osadky_veher`),
  KEY `Napravlenie_vetra` (`Napravlenie_vetra`),
  CONSTRAINT `pogoda_ibfk_1` FOREIGN KEY (`id_punkt`) REFERENCES `naselennay_punkt` (`id_punkt`),
  CONSTRAINT `pogoda_ibfk_2` FOREIGN KEY (`id_osadky_noh`) REFERENCES `atmosfernae_yvleniy` (`id_osadky`),
  CONSTRAINT `pogoda_ibfk_3` FOREIGN KEY (`id_osadky_utro`) REFERENCES `atmosfernae_yvleniy` (`id_osadky`),
  CONSTRAINT `pogoda_ibfk_4` FOREIGN KEY (`id_osadky_day`) REFERENCES `atmosfernae_yvleniy` (`id_osadky`),
  CONSTRAINT `pogoda_ibfk_5` FOREIGN KEY (`id_osadky_veher`) REFERENCES `atmosfernae_yvleniy` (`id_osadky`),
  CONSTRAINT `pogoda_ibfk_6` FOREIGN KEY (`Napravlenie_vetra`) REFERENCES `veter` (`id_veter`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

LOCK TABLES `pogoda` WRITE;
INSERT INTO `pogoda` VALUES (1,1,'2001-01-20',10,10,15,25,45,760,1,2,3,7,23,3,18),(2,2,'2001-01-20',8,6,12,15,55,770,11,2,3,7,15,2,17),(3,3,'2001-01-20',-15,-17,-10,-11,45,760,1,2,2,2,14,3,8),(4,4,'2001-01-20',-35,-40,-38,-39,50,740,1,2,3,3,8,5,3),(5,5,'2001-01-20',10,10,15,12,65,760,1,2,2,3,7,2,15),(6,6,'2001-01-20',-30,-32,-28,-30,45,770,1,2,8,8,10,4,3),(7,7,'2001-01-20',20,19,25,24,55,760,1,2,8,8,9,3,25),(8,8,'2017-10-12',25,27,35,33,60,760,1,2,4,5,12,3,28),(12,9,'2001-01-20',25,20,25,29,75,760,1,2,8,8,9,3,28);
UNLOCK TABLES;



CREATE TABLE `users` (
  `id_users` int(11) NOT NULL AUTO_INCREMENT,
  `Login` varchar(20) NOT NULL,
  `Password` varchar(10) NOT NULL,
  PRIMARY KEY (`id_users`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'Admin@','12345');
UNLOCK TABLES;


DROP TABLE IF EXISTS `veter`;
CREATE TABLE `veter` (
  `id_veter` int(11) NOT NULL AUTO_INCREMENT,
  `Napravlenie_vetra` varchar(21) NOT NULL,
  `Picture` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_veter`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


LOCK TABLES `veter` WRITE;
INSERT INTO `veter` VALUES (1,'С','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Северный.png'),(2,'Ю','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Южный.jpg'),(3,'В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(4,'З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(5,'С/В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Ю_З___С_В.png'),(6,'С/З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(7,'Ю/В','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Все_направл_ветра2.jpg'),(8,'Ю/З','D:\\Учеба ФМИТ 2 курс\\БД\\Проект\\Рисунки_направление_ветра\\Ю_З___С_В.png');
