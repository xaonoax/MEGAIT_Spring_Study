package com.megait.example.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.megait.example.ExampleApplication;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ExampleApplication.class})
@RunWith( SpringJUnit4ClassRunner.class )
@Slf4j
public class BoardControllerTest {
	// 가짜(모조) MVC
	// 마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어 준다.
	
	private MockMvc mockMvc;
	
	// 요청을 처리해주는 WebApplicationContext를 불러온다.
	@Autowired
	private WebApplicationContext WebApplicationContext;
	
	// @Before는 테스트 셋업의 5총사 중 하나
	// 각 테스트 유닛이 돌기 전 공통적으로 수행
	@Before
	public void setUp() { 
		// 가짜 MVC에 WebApplicationContext를 전달한 후 환경 생성
		mockMvc = MockMvcBuilders.webAppContextSetup(WebApplicationContext).build();
	}
	
	// 해당 경로의 응답페이지가 없다면 오류 발생
//	@Test
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list"))
						.andReturn().getModelAndView().getModelMap().toString());
	}
	
	@Test
	public void testListWithPaging() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
						.param("pageNum", "1").param("amount", "7"))
						.andReturn().getModelAndView().getModelMap().toString());
	}
	
//	@Test
	public void testRegister() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "테스트 제목").param("abcdef", "hahaha")
				.param("content", "테스트 내용")
				.param("writer", "Aon")).andReturn().getFlashMap().toString();
	}
	
	@Test
	public void testGet() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders
						.get("/board/get")
						.param("bno", "13"))
					.andReturn()
					.getModelAndView()
					.getModelMap()
					.toString());
	}
	
	@Test
	public void testModify() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "7")
				.param("title", "수정된 테스트 제목")
				.param("content", "수정된 테스트 내용")
				.param("writer", "Aon아님")).andReturn().getFlashMap().toString();
		log.info("[testModify()] " + result);
	}
	
	@Test
	public void testRemove() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "13")).andReturn().getModelAndView().getModelMap().toString();
		log.info("[testRemove()] " + result);
	}
}
