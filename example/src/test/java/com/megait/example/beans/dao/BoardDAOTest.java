package com.megait.example.beans.dao;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.megait.example.beans.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class BoardDAOTest {
	@Autowired
	BoardDAO boardDAO;
	
	@Disabled
//	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		
		board.setTitle("진짜 새로");
		board.setContent("진짜 새로 작성한 글");
		board.setWriter("GhostWriter");
		
		boardDAO.register(board);
		
		log.info("[Register BNO] " + board.getBno());
	}
	
	@Test
	public void testGet() {
		Long bno = 4L;
		log.info("[testGet] " + boardDAO.get(bno).toString());
	}
	
	@Test
	public void testGetList() {
		boardDAO.getList().forEach(board -> log.info("[testGetList] " + board.toString()));
	}
	
//	@Test
	public void testModify() {
		BoardVO board = boardDAO.get(1L);
		
		if (board == null) {
			log.info("[testModify] Target Not Found!");
			return;
		}
		
		board.setTitle("진짜 진짜 수정함");
		log.info("[testModify]" + (boardDAO.modify(board)? "성공" : "실패"));
	}
	
//	@Test
	public void testRemove() {
		Long bno = 11L;
		BoardVO board = boardDAO.get(bno);
		
		if(board == null) {
			log.info("[testRemove] Target Not Found!");
			return;
		}
		
		log.info("[testRemove] " + (boardDAO.remove(bno)? "성공" : "실패"));
	}
}
