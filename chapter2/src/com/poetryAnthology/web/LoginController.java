package com.poetryAnthology.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController{
	
    
	@RequestMapping(value = "/index.html")
	public String loginPage(){
		return "bookshelf";
	}
	
}
