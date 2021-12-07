package com.yg.portfolio.repository;


import org.apache.ibatis.annotations.Mapper;
import com.yg.portfolio.model.Cart;

@Mapper
public interface CartRepository {

	// 장바구니 저장
	void cartSave(Cart saveList);
	
}
