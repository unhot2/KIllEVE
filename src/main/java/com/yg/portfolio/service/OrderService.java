package com.yg.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.Order;
import com.yg.portfolio.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order orderDetail(int cartNo) {
		return orderRepository.orderDetail(cartNo);
	}

	public void orderSave(KakaoPay kakaopay) {
		orderRepository.orderSave(kakaopay);
	}

	public void orderList(String userId) {
		orderRepository.orderList(userId);
		
	}
}
