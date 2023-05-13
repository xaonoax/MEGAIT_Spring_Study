package com.megait.example.beans.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megait.example.beans.vo.Criteria;
import com.megait.example.beans.vo.ReplyVO;
import com.megait.example.mappers.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReplyDAO {
	@Autowired
	private ReplyMapper mapper;
	
	public int register(ReplyVO reply) {
		log.info("register.................. : " + reply);
		return mapper.insert(reply);
	}
	
	public ReplyVO get(Long rno) {
		log.info("get.................. : " + rno);
		return mapper.read(rno);
	}
	
	public int modify(ReplyVO reply) {
		log.info("modify.................. : " + reply);
		return mapper.update(reply);
	}
	
	public int remove(Long rno) {
		log.info("remove.................. : " + rno);
		return mapper.delete(rno);
	}
	
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("get Reply List.......... : " + bno);
		cri.setParam();
		return mapper.getListWithPaging(cri, bno);
	}
}
