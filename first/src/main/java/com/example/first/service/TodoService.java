package com.example.first.service;

import java.util.List;
import java.util.Optional;

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
		return output(entity.getUserId());
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
	
	public List<TodoEntity> update(final TodoEntity entity) {
		// 1 저장할 엔티티가 유효한지 확인한다.
		validate(entity);
		// 2 넘겨받은 엔티티id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		
		if(original.isPresent()) {
			// 3 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			// 4 데이터베이스에 새 값을 저장한다.
			repository.save(todo);
		}
		// output Todo에서 만든 메소드를 이용해 사용자의 모든 Todo리스트를 리턴한다.
		return output(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity) {
		// 1 저장할 엔티티가 유효한지 확인한다.
		validate(entity);
		try {
			// 2 엔티티를 삭제한다.
			repository.delete(entity);
		} catch(Exception e) {
			// 3 exception 발생 시 id와 exception을 로깅한다.
			log.error("error deleting entity ", entity.getId(), e);
			/* 4 컨트롤러로 exception을 보낸다. 데이터베이스 내부 조직을 캡슐화하려면 
			e를 리턴하지 않고 새 exception 오브젝트를 리턴한다. */
			throw new RuntimeException("error deleting entity " + entity.getId());
		}
		// 5 새 Todo 리스트를 가져와 리턴한다.
		return output(entity.getUserId());
	}
}
