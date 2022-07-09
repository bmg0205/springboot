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
public class TodoEntity {
	private String id; // 이 오브젝트의 아이디
	private String userId; // 이 오브젝트르 생성한 사용자의 아이디 
	private String title; // Todo 타이틀 (예 : 운동하기)
	private boolean done; // true - todo를 완료한 경우(checked)
}
