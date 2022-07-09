package com.example.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("test")//¸®¼Ò½º
public class TestController {

	@GetMapping
	public String testController() {
		return "Hello world!";
	}
}
