package com.megait.example.beans.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megait.example.beans.vo.BoardVO;
import com.megait.example.beans.vo.Criteria;
import com.megait.example.mappers.BoardMapper;

import lombok.RequiredArgsConstructor;

@Repository  // @Component의 자식 annotation이며, DAO에 사용한다.
@RequiredArgsConstructor
public class BoardDAO {
	@Autowired
	private BoardMapper mapper;
	
	// 게시글 입력
	public void register(BoardVO board) {
		mapper.insertSelectKey_bno(board);
	}
	
	// 게시글 가져오기
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}
	
	// 게시글 삭제
	public boolean remove(Long bno) {
		return mapper.delete(bno) != 0;
	}
	
	// 게시글 수정
	public boolean modify(BoardVO board) {
		return mapper.update(board) != 0;
	}
	
	// 게시글 갯수 가져오기
	public int getTotal(Criteria cri) {
		return mapper.getTotal(cri);
	}
	
	// 전체 게시글 가져오기(페이징)
	public List<BoardVO> getList(Criteria cri) {
		cri.setParam();
		return mapper.getListWithPaging(cri);
	}
	
	// 전체 게시글 가져오기
	public List<BoardVO> getList() {
	return mapper.getList();
	}
}
