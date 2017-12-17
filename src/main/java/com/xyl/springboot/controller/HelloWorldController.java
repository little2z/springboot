package com.xyl.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xyl.springboot.exception.MyException;

@Controller
public class HelloWorldController {

	@RequestMapping("/")
	public String home(Model model) {

		model.addAttribute("host", "http://www.ityouknow.com/spring-boot");
		return "index";
	}

	@RequestMapping("/hello")
	public String index() throws Exception {

		throw new Exception("异常");
	}
	
	@RequestMapping("/customError")
	public String error() throws Exception {

		throw new MyException("自定义异常");
	}
	
}
