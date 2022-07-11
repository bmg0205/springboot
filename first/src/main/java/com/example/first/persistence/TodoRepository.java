package com.example.first.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.first.model.TodoEntity;

//Repository는 interface이고 또 JpaRepository 를 상속받는다
//이때 JpaRepository<T, ID>가 Generic Type 을 받는 것을 주의하자
//T에는 테이블에 매핑될 엔테티 클래스이고 ID는 이 엔티티의 기본키의 타입이다. 
@Repository //Component 어노테이션의 특별 케이스 ==> 스프링이 관리한다.
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

   // 원래는 자동으로 JPA가 메서드 이름을 파싱해서 만들어줌. 복잡한 쿼리는 @Query를 이용해 지정할 수 있다.
   // 메서드이름은 쿼리, 매개변수는 쿼리에 Where에 들어갈 값을 의미 
   // ?1은 매서드의 매개변수의 순서 위치다.
   //@Query("select * from Todo t where t.userId = ?1")
   List<TodoEntity> findByUserId(String userId);
   
}
// 메서드 이름 작성 방법과 예제는 다음 공식 사이트의 래퍼런스를 통해 확인할 수 있다.
// https://docs.spring.io/spring-data/jpa/docs/current/referene/html/#jpa.query-methods.query-creation
