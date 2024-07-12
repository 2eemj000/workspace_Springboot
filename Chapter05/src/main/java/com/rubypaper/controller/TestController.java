// 순서 : board, repository, application properties, repositoryTest, datainit(data만들기), controller

package com.rubypaper.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.Board;
import com.rubypaper.persistance.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {
	private final BoardRepository boardRepo;
	
	@GetMapping("/board")
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	@GetMapping("/board/{seq}") // url이 같으면 error뜸 (@PathVariable-경로변수 사용해서 설정)
	public Board getBoard(@PathVariable Long seq) { // 이름이 같으면 "name=" 생략가능
		return boardRepo.findById(seq).get();
	}
//	@PostMapping("/board")
//	public Board postBoard(Board board) {
//		boardRepo.findById(board.getSeq());
//        board.setTitle("제목수정");
//        return boardRepo.save(board);
//    }
	@PostMapping("/board")
	public Board postBoard(@RequestBody Board board) {
		// @ RequestBody : json형태로 변환 
		// --- 그러니까 body->raw->json 형식으로 입력해줘야 함
        return boardRepo.save(board);
    }
	@PutMapping("/board")
	public Board putBoard(@RequestBody Board board) {
		
		Board b = boardRepo.findById(board.getSeq()).orElseThrow();
		if (board.getTitle() != null) b.setTitle(board.getTitle());
		if (board.getContent() != null) b.setContent(board.getContent());
		
		return boardRepo.save(b);
	}
//	@DeleteMapping("/board/{seq}")
//	public List<Board> deleteBoard(@PathVariable Long seq) {
//		boardRepo.deleteById(seq);
//		return boardRepo.findAll();
//	}
	@DeleteMapping("/board/{seq}")
	public Board deleteBoard(@PathVariable Long seq) {
		Board b = boardRepo.findById(seq).orElseThrow(); // 삭제하기전에 읽어야 삭제 후 띄울수있음
		boardRepo.deleteById(seq);
		return b;
	}
	
}
