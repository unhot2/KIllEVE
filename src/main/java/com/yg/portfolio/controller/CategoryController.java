package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yg.portfolio.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/new")
	public String categoryNew() {
		return "/category/new";
	}
	@GetMapping("/best")
	public String categoryBest() {
		return "/category/best";
	}
	@GetMapping("/outer")
	public String categoryOuter() {
		return "/category/outer";
	}
	@GetMapping("/top")
	public String categoryTop() {
		return "/category/top";
	}
	@GetMapping("/pants")
	public String categoryPants() {
		return "/category/pants";
	}
	
}
