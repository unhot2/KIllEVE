package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
	private int productNo; 		// 상품번호
	private int quantity;		// 재고량
	private String color;		// 색상	
	private String size;		// 사이즈
	private String productName;	// 상품명
	private int salePrice;		// 판매가		
	private String mainImage;	// 메인이미지
}
