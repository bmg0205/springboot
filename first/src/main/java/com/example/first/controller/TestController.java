package com.example.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("test")//���ҽ�
public class TestController {

	@GetMapping
	public String testController() {
		return "Hello world!";
	}
}
