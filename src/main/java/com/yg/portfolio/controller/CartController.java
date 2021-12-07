package com.yg.portfolio.controller;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yg.portfolio.model.Cart;
import com.yg.portfolio.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	// 장바구니 
	@GetMapping("/cartList")
	public String cartList() {
		return "/cart/cart";
	}
	
	@PostMapping("/cartSave")
	@ResponseBody
	public String cartSave(@RequestParam(value="cartList") String cartlist) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		// JSON ARRAY로 변환
		JSONArray jsonList = (JSONArray)jsonParser.parse(cartlist);
		ArrayList<Cart> saveList = new ArrayList<Cart>();
		for (Object object : jsonList) {
			// JSON ARRAY에 들어있는 오브젝트를 JSON OBJECT로 변환
			JSONObject jsonObject = (JSONObject) object;
			// CART 에 저장
			Cart testCart = new Cart();
			testCart.setProductNo(Integer.parseInt(String.valueOf(jsonObject.get("productNo"))));
			testCart.setSize((String)jsonObject.get("size"));
			testCart.setColor((String)jsonObject.get("color"));
			testCart.setQuantity(Integer.parseInt(String.valueOf(jsonObject.get("quantity"))));
			// saveList에 저장
			saveList.add(testCart);
		}
		 
		for (Cart list : saveList) {
			// 장바구니 저장
			cartService.cartSave(list);
		}
		return "redirect:/cart/cartList";
	}
	
}
