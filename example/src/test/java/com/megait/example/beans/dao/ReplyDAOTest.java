package com.megait.example.beans.dao;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.megait.example.beans.vo.Criteria;
import com.megait.example.beans.vo.ReplyVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@RunWith( SpringJUnit4ClassRunner.class )
public class ReplyDAOTest {
	
	@Autowired
	private ReplyDAO replyDAO;
	
	private Long[] arBno = {1L, 2L, 4L, 22L, 26L};
	
	@Test
	public void testRegister() {
		// 5개의 게시글에 댓글 2개씩 달기
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO reply = new ReplyVO();
			
			reply.setBno(arBno[i % 5]);
			reply.setReply("댓글 자동생성" + i);
			reply.setReplier("Robot.DAO" + i);
			
			replyDAO.register(reply);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 42L;
		ReplyVO reply = replyDAO.get(targetRno);
		
		log.info("replyDAO - get ------------------");
		log.info(reply.toString());
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 10000L;
		
		int deleteCount = replyDAO.remove(targetRno);
		log.info("replyDAO - delete --------------");
		log.info("delete count : " + deleteCount);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 49L;
		
		ReplyVO reply = replyDAO.get(targetRno);
		reply.setReply("Update했음2");
		int updateCount = replyDAO.modify(reply);
		log.info("replyDAO - update --------------");
		log.info("update count : " + updateCount);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = replyDAO.getList(cri, arBno[1]);
		log.info("replyDAO - getList --------------");
		replies.forEach(reply -> log.info(reply.toString()));
	}
}
