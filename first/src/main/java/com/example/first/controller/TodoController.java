package com.example.first.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.first.dto.ResponseDTO;
import com.example.first.dto.TodoDTO;
import com.example.first.model.TodoEntity;
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
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
		try {
			String temporaryUserId = "temporary-user"; //temporary user id.
			
			// 1 TodoEntity로 변환한다
			TodoEntity entity = TodoDTO.toEntity(dto);
			//2 id를 null로 초기화한다. 생성 당시에는 id가 없어야 하기 떄문이다.
			entity.setId(null);
			//3 임시 사용자가 아이디를 설정해 준다.
			entity.setUserId(temporaryUserId);
			//4 서비스를 이용해 Todo엔티티를 생성한다.
			List<TodoEntity> entities = service.create(entity);
			//5 자바 스트링을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			// 6 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			// 7 ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		}catch (Exception e) {
			// 8 혹시 예외가 있는 경우 dto 대신 error에 메시지를 넣어 리턴한다.
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> outputTodoList() {
		String temporaryUserId = "temporary-user"; //temporary user id.
		
		// 1 서비스 메서드의 retrieve() 메소드를 사용해 Todo리스트를 가져온다.
		List<TodoEntity> entities = service.output(temporaryUserId);
		// 2 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		// 3 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		// 4 ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}
}
