package com.example.first.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor //디폴트 생성자 매개변수 없느 생성자
@AllArgsConstructor
@Data
@Entity // 엔티티에 이름을 부여하고 싶다면 ("TodoEntity")처럼 매개변수를 넣어줄 수 있다.
@Table(name = "Todo") // 이 엔티티는 데이터베이스의 Todo 테이블에 매핑된다는 뜻이다. 명시하지않을 경우 @Entity의 이름을 테이블 이름으로 간주한다. 또는 클래스의 이름을 테이블 이름으로 간주한다.
public class TodoEntity {
	@Id // 여기서는 id가 기본키이므로 어노테이션으로 추가했다.
	@GeneratedValue(generator="system-uuid") // ID를 자동으로 생성, generator는 어떤 방식으로 id를 생성할지 지정할 수 있음
	@GenericGenerator(name="system-uuid", strategy = "uuid") // 127p
	private String id; // 이 오브젝트의 아이디
	private String userId; // 이 오브젝트르 생성한 사용자의 아이디 
	private String title; // Todo 타이틀 (예 : 운동하기)
	private boolean done; // true - todo를 완료한 경우(checked)
}
