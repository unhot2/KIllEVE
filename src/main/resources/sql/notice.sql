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



INSERT INTO yg.notice (writeDate,userId,userName,title,content,count,updateUser,updateTime) VALUES
	 ('2021-02-26 21:47:02.0','manager','매니저','2월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',324,NULL,NULL),
	 ('2021-03-20 21:47:38.0','manager','매니저','3월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',51,NULL,NULL),
	 ('2021-04-19 21:48:16.0','manager','매니저','4월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',874,NULL,NULL),
	 ('2021-05-20 21:48:27.0','manager','매니저','5월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',245,NULL,NULL),
	 ('2021-06-21 21:48:37.0','manager','매니저','6월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',57,NULL,NULL),
	 ('2021-07-24 21:48:50.0','manager','매니저','7월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',187,NULL,NULL),
	 ('2021-08-25 21:49:01.0','manager','매니저','8월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',152,NULL,NULL),
	 ('2021-09-20 21:49:16.0','manager','매니저','9월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20200203/2월 카드_shop1_101852.jpg" alt="" width="638" height="516" /></p>',344,NULL,NULL),
	 ('2021-09-26 21:49:28.0','manager','매니저','추석연휴 배송안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20180921/ECB694EC849DEC97B0ED9CB42.jpg" alt="" width="450" height="574" /></p>',1875,NULL,NULL),
	 ('2021-10-05 21:51:00.0','manager','매니저','우리은행 금융거래중단 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20180503/copy-1525335931-ED8E98EC9DB4ECA780m2.jpg" alt="" width="400" height="616" /></p>
<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20180503/copy-1525334400-ED8E98EC9DB4ECA780.jpg" alt="" width="1020" height="1214" /></p>',665,NULL,NULL),
	 ('2021-10-15 21:51:41.0','manager','매니저','10월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20181001/1985697482_RYBdP10e_10EC9B94_EBACB4EC9DB4EC9E90.png" alt="" width="638" height="537" /></p>',210,NULL,NULL),
	 ('2021-10-28 21:52:11.0','manager','매니저','킬이브 상담시간 변경안내','<p><img src="http://rooseoin.com/web/upload/NNEditor/20180201/copy-1517469454-EC8381EB8BB4EC8B9CEAB0843.jpg" alt="" width="300" height="300" /></p>',708,NULL,NULL),
	 ('2021-11-26 21:52:38.0','manager','매니저','11월 무이자카드 안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20181001/1985697482_RYBdP10e_10EC9B94_EBACB4EC9DB4EC9E90.png" alt="" width="638" height="537" /></p>',50,NULL,NULL),
	 ('2022-01-02 21:53:09.0','manager','매니저','회원등급별 혜택안내','<p><img src="https://rooseoin.co.kr/web/upload/NNEditor/20181005/EC9DB4EBAFB8ECA78020220.png" alt="" width="1283" height="650" /></p>',1542,NULL,NULL);