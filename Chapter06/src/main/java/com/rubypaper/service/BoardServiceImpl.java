package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService { 
	// BoardServiceImpl는 BoardRepository 객체를 의존성주입을 통해 참조
	// getBoardList()에서 findAll로 board의 모든 데이터를 가져와서 리턴
	
	@Autowired
	private BoardRepository boardRepo;
	
	public List<Board> getBoardList(Board board){
		return (List<Board>) boardRepo.findAll();
	}
	
	public void insertBoard(Board board) {
	}
	
	public Board getBoard(Board board) {
		return null;
	}
	
	public void updateBoard(Board board) {
	}
	
	public void deleteBoard(Board board) {
	}
	
	
}
