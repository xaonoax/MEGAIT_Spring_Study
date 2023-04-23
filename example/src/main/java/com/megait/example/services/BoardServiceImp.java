package com.megait.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megait.example.beans.dao.BoardDAO;
import com.megait.example.beans.vo.BoardVO;

@Service
public class BoardServiceImp implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void register(BoardVO board) {
		boardDAO.register(board);
	};
	
	@Override
	public BoardVO get(Long bno) {
		return boardDAO.get(bno);
	};
	
	@Override
	public boolean modify(BoardVO board) {
		return boardDAO.modify(board);
	};
	
	@Override
	public boolean remove(Long bno) {
		return boardDAO.remove(bno);
	};
	
	@Override
	public List<BoardVO> getList(){
		return boardDAO.getList();
	};
}
