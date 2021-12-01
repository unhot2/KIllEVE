package com.yg.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.portfolio.model.Product;
import com.yg.portfolio.model.ProductImg;
import com.yg.portfolio.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/detail/{productNo}")
	public String productDetail(Model model, @PathVariable int productNo) {
		Product detailList = productService.productInfo(productNo);
		model.addAttribute("detail",detailList);
		List<ProductImg> imgDetail = productService.productImg(productNo); 
		model.addAttribute("imgDetail",imgDetail);
		return "/product/productDetailForm";
	}
}
