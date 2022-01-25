CREATE TABLE `productColor` (
  `colorNo` int(11) NOT NULL AUTO_INCREMENT,
  `productNo` int(11) NOT NULL,
  `colorName` varchar(100) NOT NULL,
  PRIMARY KEY (`colorNo`),
  KEY `productColor_FK` (`productNo`),
  CONSTRAINT `productColor_FK` FOREIGN KEY (`productNo`) REFERENCES `product` (`productNo`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

INSERT INTO yg.productColor (productNo,colorName) 
VALUES (1,'블랙'),
	   (1,'아이보리'),
	   (1,'차콜'),
	   (1,'네이비'),
	   (2,'블랙'),
	   (2,'카키'),
	   (2,'차콜'),
	   (3,'베이지'),
	   (3,'차콜'),
	   (3,'카키'),
	   (3,'블랙'),
	   (4,'블랙'),
	   (4,'카키'),
	   (5,'블랙'),
	   (5,'베이지'),
	   (5,'아이보리'),
	   (6,'블랙'),
	   (6,'그레이'),
	   (7,'블랙'),
	   (7,'딥블루'),
	   (7,'아이보리'),
	   (7,'다크그레이'),
	   (7,'연베이지'),
	   (8,'그레이'),
	   (8,'네이비'),
	   (8,'블랙'),
	   (9,'블랙'),
	   (9,'그레이'),
	   (9,'베이지'),
	   (9,'브라운'),
	   (9,'카키'),
	   (10,'화이트'),
	   (10,'아이보리'),
	   (10,'블랙'),
	   (10,'베이지'),
	   (10,'브라운'),
	   (10,'네이비'),
	   (10,'그레이'),
	   (11,'블랙'),
	   (11,'블루'),
	   (11,'카키'),
	   (11,'아이보리'),
	   (12,'블랙'),
	   (12,'아이보리'),
	   (13,'블랙'),
	   (13,'네이비'),
	   (14,'베이지'),
	   (14,'카키'),
	   (15,'그레이'),
	   (15,'연그레이'),
	   (15,'블랙'),
	   (15,'오트밀'),
	   (16,'메란지'),
	   (16,'블랙'),
	   (16,'백오트'),
	   (16,'네이비'),
	   (17,'차콜'),
	   (17,'네이비'),
	   (17,'베이지'),
	   (17,'브라운'),
	   (18,'그레이'),
	   (18,'블랙'),
	   (18,'오트밀'),
	   (18,'차콜'),
	   (19,'카키'),
	   (19,'블랙'),
	   (19,'아이보리'),
	   (20,'블랙'),
	   (20,'그레이'),
	   (20,'네이비'),
	   (21,'블랙'),
	   (21,'차콜'),
	   (21,'베이지'),
	   (21,'카키'),
	   (21,'연베이지'),
	   (22,'블랙'),
	   (22,'네이비'),
	   (22,'베이지'),
	   (22,'그레이'),
	   (23,'블랙'),
	   (24,'블랙'),
	   (24,'블루'),
	   (24,'그레이'),
	   (25,'화이트멜란지'),
	   (25,'멜란지'),
	   (25,'네이비'),
	   (25,'블랙'),
	   (26,'복스소가죽'),
	   (26,'PU'),
	   (27,'블랙'),
	   (27,'화이트'),
	   (27,'베이지'),
	   (27,'옐로우'),
	   (27,'블루'),
	   (27,'와인'),
	   (28,'복스소가죽'),
	   (28,'PU'),
	   (29,'고급소가죽'),
	   (29,'에코가죽(PU)'),
	   (30,'화이트'),
	   (30,'네이비'),
	   (31,'화이트'),
	   (32,'레드브라운'),
	   (32,'다크브라운'),
	   (33,'아이보리'),
	   (34,'카키'),
	   (34,'베이지그레이'),
	   (34,'크림베이지'),
	   (34,'블루'),
	   (34,'소라'),
	   (34,'블랙'),
	   (35,'블랙'),
	   (35,'블루'),
	   (35,'아이보리'),
	   (35,'차콜');