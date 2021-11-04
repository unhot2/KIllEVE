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
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping("/home")
	public String index(@RequestParam String name,Model model, HttpSession session) {
		System.out.println(model);
		System.out.println("넘어온 name값 : ");
		System.out.println(name);
		String address = dao.getUser(name);
		System.out.print("돌아온 주소값 : ");
		System.out.println(address);
		model.addAttribute("text",name+"의 주소는 "+address+"입니다");
		session.setAttribute("sessionTest", "Y");
		return "home";
	}
}
