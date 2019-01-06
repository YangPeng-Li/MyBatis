package com.lyp.mybatis.dao;

import org.apache.ibatis.annotations.Select;

import com.lyp.mybatis.bean.Employee;

public interface EmployeeMapperAnnotation {
	
	
	@Select("select * from tbl_employee where id = #{id}")
	public Employee getEmpById (Integer id);

}
