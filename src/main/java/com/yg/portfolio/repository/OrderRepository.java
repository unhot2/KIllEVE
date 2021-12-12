package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;

import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.Order;

@Mapper
public interface OrderRepository {
	
	
	// 주문서 상세정보
	Order orderDetail(int cartNo);

	// 주문 저장
	void orderSave(KakaoPay kakaopay);

	
}
