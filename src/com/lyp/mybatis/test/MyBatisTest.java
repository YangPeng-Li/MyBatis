package com.lyp.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lyp.mybatis.bean.Employee;
import com.lyp.mybatis.dao.EmployeeMapper;
import com.lyp.mybatis.dao.EmployeeMapperAnnotation;

public class MyBatisTest {

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String gloableConfig = "mybatis-config.xml";
		InputStream resourceAsStream = Resources.getResourceAsStream(gloableConfig);
		return new SqlSessionFactoryBuilder().build(resourceAsStream);

	}

	@Test
	public void Test01() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		System.out.println("sqlSessionFactory-------->" + sqlSessionFactory);
		SqlSession openSession = sqlSessionFactory.openSession();
		System.out.println("openSession" + openSession);
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			System.out.println(mapper);
			Employee emp = mapper.getEmpById(2);
			System.out.println(emp);
		} finally {
			openSession.close();
		}
	}

	@Test
	public void testAnnotation() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
			System.out.println(mapper.getClass());
			Employee empById = mapper.getEmpById(1);
			System.out.println(empById);
		} finally {
			openSession.close();
		}
	}

	/**
	 * 测试增删改
	 * 1、mybatis 允许增删改直接定义一下类型
	 * 	Integer、Long、Boolean、 Void
	 * 2、我们需要手动提交数据
	 * sqlSessionFactory.openSession(); === >手动提交
	 * sqlSessionFactroy.openSeessin(true);====>自动提交 
	 * 
	 * @throws IOException
	 */
	@Test
	public void test03ADD() throws IOException {
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

		SqlSession openSession = sqlSessionFactory.openSession();
		try
		{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			Employee emp = new Employee(null,"Stark","Stark@Gmail.com",1);
			mapper.addEmp(emp);
			openSession.commit();
			System.out.println("Test03ADD------->"+emp.getId());
		} finally {
			openSession.close();
		}
	}
	
	@Test
	public void test03Delete () throws IOException
	{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try 
		{
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			mapper.deleteEmpById(2);
			openSession.commit();
		}
		finally
		{
			openSession.close();
		}
	}
	
	@Test
	public void test03Update () throws IOException
	{
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
		EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
		Employee p2 = new  Employee(3,"Lucy","Lucy@Outlook.com",0);
		mapper.updateEmp(p2);
		openSession.commit();
		}
		finally
		{
			openSession.close();
		}
		
	}
	
	
}
