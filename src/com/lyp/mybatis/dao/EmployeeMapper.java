package com.lyp.mybatis.dao;

import com.lyp.mybatis.bean.Employee;

public interface EmployeeMapper {
	
	public Employee getEmpById (Integer id);
	
	public void addEmp(Employee employee);
	
	public void deleteEmpById (Integer id);
	
	public void updateEmp(Employee employee);

	
}
