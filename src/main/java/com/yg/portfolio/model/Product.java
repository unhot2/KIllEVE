package com.yg.portfolio.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
	private int productNo;
	private String category;
	private String productName;
	private int price;
	private int stock;
}
