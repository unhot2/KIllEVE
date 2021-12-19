package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yg.portfolio.service.ProductService;

@Controller
public class PortfolioController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping({"","/"})
	public String index(Model model) {
		model.addAttribute("bestList",productService.productBest());
		model.addAttribute("newList",productService.productNew());
		return "index";
	}
	
}
