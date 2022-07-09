package com.example.first.dto;

import com.example.first.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;

	
	//TodoEntity ���� TodoDTO �� ��ȯ�ϴ°�
	//�ڷᰡ �����Ǹ� �ȵǴϱ� final
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
}
