package com.yg.portfolio.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderList {
	private List<Order> orderList;
}

