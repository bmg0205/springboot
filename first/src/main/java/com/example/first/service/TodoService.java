package com.example.first.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.model.TodoEntity;
import com.example.first.persistence.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	//public String testService() {
		//return "Test Service";
	//}

	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		// TodoEntity 저장
		repository.save(entity);
		// TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}
}
