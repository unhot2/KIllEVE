package com.yg.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yg.portfolio.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/new")
	public String categoryNew(@RequestParam(value = "currentPage", required = false) Integer currentPage, Model model) {
		model.addAttribute("newList",categoryService.categoryNew(currentPage,model));
		return "/category/new";
	}
	
	@GetMapping("/best")
	public String categoryBest(@RequestParam(value = "currentPage", required = false) Integer currentPage, Model model) {
		model.addAttribute("bestList",categoryService.categoryBest(currentPage,model));
		return "/category/best";
	}
	
	@GetMapping("/outer")
	public String categoryOuter(Model model
			, @RequestParam(value = "currentPage", required = false) Integer currentPage
			, @RequestParam(value = "category", required = false) String category) {
		model.addAttribute("outerList",categoryService.categoryOuter(currentPage,category,model));
		return "/category/outer";
	}
	
	@GetMapping("/top")
	public String categoryTop(Model model
			, @RequestParam(value = "currentPage", required = false) Integer currentPage
			, @RequestParam(value = "category", required = false) String category) {
		model.addAttribute("topList",categoryService.categoryTop(currentPage,category,model));
		return "/category/top";
	}
	
	@GetMapping("/pants")
	public String categoryPants(Model model
			, @RequestParam(value = "currentPage", required = false) Integer currentPage
			, @RequestParam(value = "category", required = false) String category) {
		model.addAttribute("pantsList",categoryService.categoryPants(currentPage,category,model));
		return "/category/pants";
	}
	
	@GetMapping("/shoes")
	public String categoryShoes(Model model
			, @RequestParam(value = "currentPage", required = false) Integer currentPage
			, @RequestParam(value = "category", required = false) String category) {
		model.addAttribute("shoesList",categoryService.categoryShoes(currentPage,category,model));
		return "/category/shoes";
	}
	
}
