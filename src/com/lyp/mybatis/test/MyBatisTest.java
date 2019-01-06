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
	 * 1��SqlSession ��������ݿ��һ�λػ����������ص���
	 * 
	 * 2��SqlSession ��Connectionһ�����Ƿ��̰߳�ȫ�ģ�ÿ��ʹ�ö���ȡ�µĶ���
	 * 
	 * 3��Mapper�ӿ�û��ʵ���࣬����mybatish��Ϊ����ӿ��ṩһ���������
	 * 		(���ӿں�xml���а�)
	 * 4��������Ҫ�������ļ�  mybatis-config.xml ��ȫ�������ļ� �������ݿ����ӳ� �����������Ϣ ϵͳ������Ϣ
	 * 		mapper.xml
	 * 		sqlӳ���ļ�  ������ÿһ��sql����ӳ����Ϣ
	 * 					��sql��ȡ����
	 *  	
	 */
	
	
	
	
	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	/**
	 * 1������xml �����ļ���ȫ��������ļ��� ����һ��SqlSessionFactory����
	 * 2��
	 * 
	 */
	@Test
	public void test01() throws IOException
	{
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession openSession = ssf.openSession();
		//2����ȡsqlSessionʵ��,��ֱ��ִ���Ѿ�ӳ���sql���
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
			System.out.println(mapper.getClass());//������
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
