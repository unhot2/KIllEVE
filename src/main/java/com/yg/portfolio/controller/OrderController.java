package com.yg.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yg.portfolio.model.Order;
import com.yg.portfolio.model.OrderList;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.MemberService;
import com.yg.portfolio.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;
	
	// 주문서 상세정보
	@GetMapping("/orderDetail")
	public String orderForm(OrderList list, Model model, HttpSession session) {
		int chk = 0;
		List<Order> orderList = new ArrayList<Order>();
		// 장바구니에서 가져온 CartNo 리스트
		for (Order order : list.getOrderList()) {
			if(order.getCheck() != null) { // checkBox가 체크되었을 경우
				int cartNo = order.getCartNo();
				orderList.add(orderService.orderDetail(cartNo)); // cart에 담긴 정보 가져와서 orderList에 추가
			}
			chk++;
		}
		User user = new User();
		user.setUserId((String) session.getAttribute("userId"));
		model.addAttribute("memberInfo",memberService.memberInfo(user));
		model.addAttribute("orderList",orderList);
		return "/order/orderForm";
	}

	@GetMapping("/orderForm")
	public String orderForm2() {
		return "/order/orderForm";
	}
}
