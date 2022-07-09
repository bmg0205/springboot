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
@NoArgsConstructor //����Ʈ ������ �Ű����� ���� ������
@AllArgsConstructor
@Data
public class TodoEntity {
	private String id; // �� ������Ʈ�� ���̵�
	private String userId; // �� ������Ʈ�� ������ ������� ���̵� 
	private String title; // Todo Ÿ��Ʋ (�� : ��ϱ�)
	private boolean done; // true - todo�� �Ϸ��� ���(checked)
}
