package com.megait.example.mybatis;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class MyBatisConfigTest {
	
	@Autowired
	private SqlSessionFactory SqlSessionFactory;
	
	@Autowired
	private DataSource dataSource;

	@Test
	public void testDataSource() {
		log.info("DataSource : " + dataSource);
		try
			( 
				Connection conn = dataSource.getConnection();
			)
		{
			log.info("-------------------------------");
			log.info("datasource connection : " + conn);
			log.info("-------------------------------");
		}
		catch (Exception e) { e.printStackTrace(); }
	}
}
