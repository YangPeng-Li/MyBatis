package com.lyp.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.lyp.mybatis.bean.Employee;
import com.lyp.mybatis.bean.employee.EmployeeMapper;

public class MyBatisTest {

	/*
	 * 1、SqlSession 代表和数据库的一次回话，用完必须关掉；
	 * 
	 * 2、SqlSession 和Connection一样都是非线程安全的，每次使用都获取新的对象
	 * 
	 * 3、Mapper接口没有实现类，但是mybatish会为这个接口提供一个代理对象
	 * 		(将接口和xml进行绑定)
	 * 4、两个重要的配置文件  mybatis-config.xml 的全局配置文件 包含数据库连接池 事务管理器信息 系统运行信息
	 * 		mapper.xml
	 * 		sql映射文件  保存了每一个sql语句的映射信息
	 * 					将sql提取出来
	 *  	
	 */
	
	
	
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	/**
	 * 1、根据xml 配置文件（全局配额制文件） 创建一个SqlSessionFactory对象
	 * 2、
	 * 
	 */
	@Test
	public void test01() throws IOException
	{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession openSession = ssf.openSession();
		//2、获取sqlSession实例,能直接执行已经映射的sql语句
//		statement Unique identifier matching the statement to use.
//		parameter A parameter object to pass to the statement.
		
		try {
			Employee selectOne = openSession.selectOne("com.lyp.mybatis.EmployeeMapper.getEmpById", 1);
			System.out.println(selectOne);
		} 
		finally  {
			openSession.close();
		}
	}
	
	@Test
	public void test02 () throws IOException
	{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession openSession = ssf.openSession();
		try {
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			System.out.println(mapper.getClass());//代理类
			Employee empById = mapper.getEmpById(1);
			System.out.println(empById);
			//Employee p = openSession.selectOne("com.lyp.mybatis.bean.employee.EmployeeMapper.getEmpById", 2);
			//System.out.println(p);
		}
		finally
		{
			openSession.close();
		}
	}
	
	
	
}
