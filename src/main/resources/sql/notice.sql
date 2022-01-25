CREATE TABLE `notice` (
  `boardNum` int(11) NOT NULL AUTO_INCREMENT,
  `writeDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `userId` varchar(100) NOT NULL,
  `userName` varchar(100) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT 0,
  `updateUser` varchar(100) DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`boardNum`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;



INSERT INTO yg.notice (writeDate,userId,userName,title,content,count,updateUser,updateTime) 
VALUES ('2021-11-19 18:42:24.0','admin','관리자','킬이브 상담시간 변경안내','2021년 2월 28일부터 킬이브 상담시간이 변경됩니다.',0,NULL,NULL),
	   ('2021-11-19 20:02:12.0','admin','관리자','1월 무이자카드 안내','<p>안녕하세요&nbsp;</p>',0,NULL,NULL),
	   ('2021-11-23 14:38:08.0','admin','관리자','2월 무이자카드 안내','<p>121321312</p>',4,NULL,NULL),
	   ('2021-11-23 16:06:14.0','manager','매니저','설연휴 배송안내','<p>설 연휴 배송건의 경우 2주의 기간이 지연될 예정입니다.</p><p>불편을 드려 죄송합니다.</p>',0,NULL,NULL),
	   ('2021-11-23 16:07:07.0','manager','매니저','우리은행 금융거래 중단안내','<p>&nbsp;</p><p>내부 사정으로 인하여 우리은행 결제가 중단됩니다.</p><p>불편을 드려 죄송합니다.</p>',0,NULL,NULL),
	   ('2021-11-23 16:09:34.0','admin','관리자','3월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:42.0','admin','관리자','4월 무이자카드 안내','무이자',1,NULL,NULL),
	   ('2021-11-23 16:09:43.0','admin','관리자','5월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:44.0','admin','관리자','6월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:45.0','admin','관리자','7월 무이자카드 안내','무이자',0,NULL,NULL),
  	   ('2021-11-23 16:09:46.0','admin','관리자','8월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:47.0','admin','관리자','9월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:48.0','admin','관리자','10월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:50.0','admin','관리자','11월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-23 16:09:52.0','admin','관리자','12월 무이자카드 안내','무이자',0,NULL,NULL),
	   ('2021-11-24 15:18:45.0','admin','관리자','사진 테스트','<p><img src="https://e-img.cafe24.com/facevt/saldrf/202104/crm_1617670968606bb3385fd39058229114197.png" alt="" width="700" height="700" /></p>',43,NULL,NULL);