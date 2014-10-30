package com.poetryAnthology.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReaderController{
    
	@RequestMapping(value = "/reader.html")
	public String loginPage(){
		return "reader";
	}
}
