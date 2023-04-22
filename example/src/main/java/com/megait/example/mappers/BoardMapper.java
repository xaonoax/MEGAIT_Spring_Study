package com.megait.example.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.megait.example.beans.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// 게시글 목록
	public List<BoardVO> getList();
}
