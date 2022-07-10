package com.example.first.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.dto.ResponseDTO;
import com.example.first.service.TodoService;

@RestController//이 클래스는 컨트롤러인데 뷰를 따로 만들지 안아도 되는 컨트롤러임을 명시하는 어노테이션
@RequestMapping("todo") // 컨트롤러를 맵핑하는 거 url 생성 
public class TodoController {

	// todoConroller를 초기화 할때 스프링은 알아서 todoService를 초기화 또는 검색해 todoController에 주입해준다.
	@Autowired //이것은 알아서 빈을 찾은 다음 이 인스턴스 멤버 변수에 연결하라는 뜻
	private TodoService service;
	
	//testTodo 메소드 작성하기
	@GetMapping("/test")
	public ResponseEntity<?> testTodo() {
		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
}
