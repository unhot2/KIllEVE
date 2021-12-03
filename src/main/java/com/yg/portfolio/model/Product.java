package com.yg.portfolio.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
	private int productNo; 		// 상품번호
	private String category; 	// 카테고리
	private String productName;	// 상품명
	private int discountRate;	// 할인율		
	private int consumerPrice;	// 소비자가		
	private int salePrice;		// 판매가		
	private int stock;			// 재고량
	private String mainImage;	// 메인이미지
}
