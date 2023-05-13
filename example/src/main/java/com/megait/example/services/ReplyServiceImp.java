package com.megait.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megait.example.beans.dao.ReplyDAO;
import com.megait.example.beans.vo.Criteria;
import com.megait.example.beans.vo.ReplyVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyServiceImp implements ReplyService {

	@Autowired
	private ReplyDAO replyDAO;
	
	@Override
	public int register(ReplyVO reply) {
		log.info("register................... : " + reply);
		return replyDAO.register(reply);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		log.info("get................... : " + rno);
		return replyDAO.get(rno);
	}
	
	@Override
	public int modify(ReplyVO reply) {
		log.info("modify................... : " + reply);
		return replyDAO.modify(reply);
	}
	
	@Override
	public int remove(Long rno) {
		log.info("remove................... : " + rno);
		return replyDAO.remove(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList................... : " + bno);
		return replyDAO.getList(cri, bno);
	}
}
