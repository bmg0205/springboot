package com.example.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.first.model.TodoEntity;
import com.example.first.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

// 모든 로그 레벨을 구현시켜주는 기능을 제공하는 라이브러리다
@Slf4j //사용하려면 구현부를 연결해줘야 한다.
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
	
	public List<TodoEntity> create(final TodoEntity entity) {
		//Validations 검증
		validate(entity);
	// 엔티티를 데이터베이스에 저장하고 로그를 남긴다
		repository.save(entity); 
		log.info("Entity Id : {} is saved.");
		// 저장된 엔티티를 포함하는 새 리스트를 리턴한다.
		return repository.findByUserId(entity.getUserId());
	}
	
	//리펙토링한 메소드
	private void validate(final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity connot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
		
		if(entity.getUserId() == null)	{
			log.warn("Unkown user.");
			throw new RuntimeException("Unkown user.");
		}
	}
	
	//output userId에 어느 한 유저가 리스트를 뽑는다.(temporary-user)
	public List<TodoEntity> output(final String userId) {
		return repository.findByUserId(userId);
	}
	
	
}
