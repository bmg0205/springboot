package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Package")
@SequenceGenerator(
        name="PACKAGE_SEQUENCE_GENERATOR", // 제너레이터명
        sequenceName="PACKAGE_SEQUENCE", // 시퀀스명
        initialValue= 1, // 시작 값
        allocationSize= 1 // 할당할 범위 사이즈
)
public class PackageEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PACKAGE_SEQUENCE_GENERATOR")
	private int pno;
	private String title;
	private int price;
	private String content;
	private Date depDate;
	private int period;
	private Date matchDate;
}
