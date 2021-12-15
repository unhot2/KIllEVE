package com.yg.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yg.portfolio.model.Cart;
import com.yg.portfolio.model.KakaoPay;
import com.yg.portfolio.model.OrderForm;
import com.yg.portfolio.model.OrderFormList;
import com.yg.portfolio.model.User;
import com.yg.portfolio.service.CartService;
import com.yg.portfolio.service.MemberService;
import com.yg.portfolio.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CartService cartService;

	// 주문내역
	@GetMapping("/orderList")
	public String orderList(User user, Model model, HttpSession session) {
		orderService.orderList((String) session.getAttribute("userId"));
		return "/order/orderList";
	}
	
	// 주문서 상세정보
	@GetMapping("/orderDetail")
	public String orderFormCart(OrderFormList list, Model model, HttpSession session
//			, @RequestParam(value="buyList", required = false) String buyList
			){
//		if(list.getOrderFormList() != null) {
			System.out.println("!=null 들어옴");
			List<OrderForm> orderList = new ArrayList<OrderForm>();
			// 장바구니에서 가져온 CartNo 리스트
			for (OrderForm order : list.getOrderFormList()) {
				if(order.getCheck() != null) { // checkBox가 체크되었을 경우
					int cartNo = order.getCartNo();
					orderList.add(orderService.orderDetail(cartNo)); // cart에 담긴 정보 가져와서 orderList에 추가
				}
			}
			User user = new User();
			user.setUserId((String) session.getAttribute("userId"));
			model.addAttribute("memberInfo",memberService.memberInfo(user));
			model.addAttribute("orderList",orderList);
//		}
//		if(buyList != null) {
//			System.out.println("buyList != null 들어옴");
//			JSONParser jsonParser = new JSONParser();
//			// JSON ARRAY로 변환
//			JSONArray jsonList = (JSONArray)jsonParser.parse(buyList);
//			ArrayList<OrderForm> orderList = new ArrayList<OrderForm>();
//			for (Object object : jsonList) {
//				// JSON ARRAY에 들어있는 오브젝트를 JSON OBJECT로 변환
//				JSONObject jsonObject = (JSONObject) object;
//				// CART 에 저장
//				OrderForm order = new OrderForm();
//				System.out.println("상품번호 : "+String.valueOf(jsonObject.get("productNo")));
//				System.out.println("상품명 : "+String.valueOf(jsonObject.get("productName")));
//				System.out.println("사이즈 : "+String.valueOf(jsonObject.get("size")));
//				System.out.println("색상 : "+String.valueOf(jsonObject.get("color")));
//				System.out.println("수량 : "+String.valueOf(jsonObject.get("quantity")));
//				System.out.println("주문자 : "+String.valueOf(jsonObject.get("userId")));
//				System.out.println("카테고리 : "+String.valueOf(jsonObject.get("category")));
//				System.out.println("메인이미지 : "+String.valueOf(jsonObject.get("mainImage")));
//				System.out.println("최종가격 : "+String.valueOf(jsonObject.get("totalPrice")));
//				System.out.println("판매가 : "+String.valueOf(jsonObject.get("salePrice")));
//				order.setProductNo(Integer.parseInt(String.valueOf(jsonObject.get("productNo"))));
//				order.setProductName(String.valueOf(jsonObject.get("productName")));
//				order.setSize((String)jsonObject.get("size"));
//				order.setColor((String)jsonObject.get("color"));
//				order.setQuantity(Integer.parseInt(String.valueOf(jsonObject.get("quantity"))));
//				order.setUserId((String)jsonObject.get("userId"));
//				order.setSalePrice(Integer.parseInt(String.valueOf(jsonObject.get("salePrice"))));
//				order.setTotalPrice(Integer.parseInt(String.valueOf(jsonObject.get("totalPrice"))));
//				order.setCategory(String.valueOf(jsonObject.get("category")));
//				order.setUserId(String.valueOf(jsonObject.get("userId")));
//				order.setMainImage(String.valueOf(jsonObject.get("mainImage")));
//				orderList.add(order);
//			}
//			User user = new User();
//			user.setUserId((String) session.getAttribute("userId"));
//			model.addAttribute("memberInfo",memberService.memberInfo(user));
//			model.addAttribute("orderList",orderList);
//		}
		return "/order/orderForm";
	}

	// 주문서 상세정보
	@GetMapping("/directOrderDetail")
	public String orderFormBuy(OrderFormList list, Model model, HttpSession session
			, @RequestParam(value="productNo") int productNo
			, @RequestParam(value="category") String category
			, @RequestParam(value="mainImage") String mainImage
			, @RequestParam(value="salePrice") int salePrice) throws ParseException {
			List<OrderForm> orderList = new ArrayList<OrderForm>();
//			System.out.println("상품번호 :"+productNo);
//			System.out.println("카테고리 :"+category);
//			System.out.println("메인이미지 :"+mainImage);
//			System.out.println("판매가 :"+salePrice);
			// 장바구니에서 가져온 CartNo 리스트
			for (OrderForm order : list.getOrderFormList()) {
				OrderForm saveList = new OrderForm();
//				System.out.println("상품명 :"+order.getProductName());
//				System.out.println("사이즈 :"+order.getSize());
//				System.out.println("색상 :"+order.getColor());
//				System.out.println("수량 :"+order.getQuantity());
				saveList.setProductNo(productNo);
				saveList.setProductName(order.getProductName());
				saveList.setSize(order.getSize());
				saveList.setColor(order.getColor());
				saveList.setQuantity(order.getQuantity());
				saveList.setCategory(category);
				saveList.setMainImage(mainImage);
				saveList.setSalePrice(salePrice);
				saveList.setTotalPrice(salePrice*order.getQuantity());
				orderList.add(saveList);
			}
			User user = new User();
			user.setUserId((String) session.getAttribute("userId"));
			System.out.println("id값 : "+(String) session.getAttribute("userId"));
			model.addAttribute("memberInfo",memberService.memberInfo(user));
			model.addAttribute("orderList",orderList);
		return "/order/orderForm";
	}

	@PostMapping("/kakaoPayment")
	public @ResponseBody String kakaoPayment(KakaoPay kakaopay, @RequestParam("test") List<String> test) {
		orderService.orderSave(kakaopay);
		// 장바구니 목록 삭제
		for (String cartNo : test) {
//			System.out.println("넘어온 cartNo값  :"+cartNo);
			cartService.cartDelete(cartNo);
		}
//		System.out.println(kakaopay.getUserId());
//		System.out.println(kakaopay.getName());
//		System.out.println(kakaopay.getAmount());
//		System.out.println(kakaopay.getDelivery_price());
//		System.out.println(kakaopay.getDelivery_name());
//		System.out.println(kakaopay.getDelivery_tel());
//		System.out.println(kakaopay.getDelivery_postcode());
//		System.out.println(kakaopay.getDelivery_addr());
//		System.out.println(kakaopay.getDelivery_message());
//		System.out.println(kakaopay.getImp_uid());
//		System.out.println(kakaopay.getMerchant_uid());
//		System.out.println(kakaopay.getPaid_at());
//		System.out.println(kakaopay.getReceipt_url());
		return kakaopay.getMerchant_uid();
	}
}
