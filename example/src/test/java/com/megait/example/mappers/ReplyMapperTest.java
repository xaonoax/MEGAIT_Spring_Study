package com.megait.example.mappers;

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
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper mapper;
	
//	@Test
	public void testMapper() {
		log.info("----------------------Mapper Test----------------------");
		log.info(mapper.toString());
	}
	
	// 자기 게시판에서 5개의 board 글을 선택하기
	private Long[] arBno = {1L, 2L, 4L, 22L, 26L};
	
//	@Test
	public void testInsert() {
		// 5개의 게시글에 댓글 2개씩 달아보기
		IntStream.rangeClosed(1, 10).forEach(i -> {
			ReplyVO reply = new ReplyVO();
			
			reply.setBno(arBno[i % 5]);
			reply.setReply("댓글 자동생성" + i);
			reply.setReplier("Robot.A" + i);
			
			mapper.insert(reply);
		});
	}
	
	@Test
	public void testRead() {
		Long targetRno = 41L;
		ReplyVO reply = mapper.read(targetRno);
		
		log.info("reply ok -------------------------------");
		log.info(reply.toString());
	}
	
	@Test
	public void testDelete() {
		Long targetRno = 44L;
		int deleteCount = mapper.delete(targetRno);
		
		log.info("reply delete ---------------------------");
		log.info("delete count : " + deleteCount);
	}
	
	@Test
	public void testUpdate() {
		Long targetRno = 49L;
		
		ReplyVO reply = mapper.read(targetRno);
		reply.setReply("Update했음");
		
		int updateCount = mapper.update(reply);
		log.info("reply update ---------------------------");
		log.info("update count : " + updateCount);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, arBno[1]);
		log.info("reply list -----------------------------");
		replies.forEach(reply -> log.info(reply.toString()));
	}
	
	@Test
	public void testGetTest() {
		ReplyVO reply = mapper.getTest(42L, 4L);
		
		log.info("reply test -------------------------------");
		log.info(reply.toString());
	}
}
