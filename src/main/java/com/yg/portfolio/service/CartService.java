package com.yg.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yg.portfolio.model.Cart;
import com.yg.portfolio.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	// 장바구니 저장
	public void cartSave(Cart list) {
		cartRepository.cartSave(list);
	}
	
}
