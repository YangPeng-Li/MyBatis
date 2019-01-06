package com.lyp.mybatis.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Employee {
	private Integer id;
	private String lastName;
	private String email;
	private Integer gender;
	public Employee(String lastName, String email, Integer gender) {
		super();
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
	}
	

	
	
}
