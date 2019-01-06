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
	private String gender;
}
