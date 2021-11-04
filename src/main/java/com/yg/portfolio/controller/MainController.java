package com.yg.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yg.portfolio.dao.UserDAO;

@Controller
public class MainController {
	@Autowired
	private UserDAO dao;
	
	@RequestMapping("/")
	public String root(Model model) {
		return "redirect:index";
	}
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session) {
		return "index";
	}
}
