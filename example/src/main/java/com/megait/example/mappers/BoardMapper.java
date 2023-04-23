package com.megait.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.megait.example.beans.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// 게시글 목록
	public List<BoardVO> getList();
	
	// 게시글 등록
	public void insert(BoardVO board);
	
	// 게시글 등록(PK 가져오기)
	public void insertSelectKey_bno(BoardVO board);
	
	// 게시글 상세보기(특정 게시글 정보)
	public BoardVO read(Long bno);
	
	// 게시글 삭제
	public int delete(Long bno);
	
	// 게시글 수정(수정이 완료된 건 수 리턴)
	public int update(BoardVO board);
}
