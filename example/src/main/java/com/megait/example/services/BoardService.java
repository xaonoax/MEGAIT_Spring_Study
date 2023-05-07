package com.megait.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.megait.example.beans.vo.BoardVO;
import com.megait.example.beans.vo.Criteria;

@Service
public interface BoardService {
	public void register(BoardVO board);
	public BoardVO get(Long bno);
	public boolean modify(BoardVO board);
	public boolean remove(Long bno);
	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	public int getTotal(Criteria cri);
}
