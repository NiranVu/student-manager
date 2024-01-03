package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	

	@GetMapping("/login")
	public String hi() {
		return "login.html";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session,
							@RequestParam("username") String username,
							@RequestParam("password") String password) {
		if (username.equals("admin") && password.equals("123")) {
			session.setAttribute("username", username);
			return "redirect:/user/list";
		} else {
			return "redirect:/login";
		}
	}
}
