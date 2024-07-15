package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rubypaper.domain.Board;
import com.rubypaper.persistance.BoardRepository;

@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;
	
//	@Test
//	public void testFindByTitle() {
//		// find+(엔티티이름)+By+변수이름(테이블 아니라 class의 필드명으로) -> JPA가 자동생성해줌
//		List<Board> list = boardRepo.findByTitle("title10");
//		System.out.println("--> testFindByTitle");
//		for (Board b : list)
//			System.out.println(b);
//	}
//	
//	@Test
//	public void testByContentContaining() {
//		List<Board> list = boardRepo.findByContentContaining("5");
//		System.out.println("--> findByContentContaining");
//		for (Board b : list)
//			System.out.println(b);
//	}
//	@Test
//	public void findByTitleContainingAndContentContaining() {
//		List<Board> list = boardRepo.findByTitleContainingAndContentContaining("5", "7");
//		System.out.println("--> findByTitleContainingAndContentContaining");
//		for (Board b : list)
//			System.out.println(b);
//	}
//	@Test
//	public void findByTitleContaining() {
//		Pageable paging = PageRequest.of(0, 5);
//		List<Board> list = boardRepo.findByTitleContaining("title", paging);
//		System.out.println("--> findByTitleContaining");
//		for (Board b : list)
//			System.out.println(b);
//	}
//	// 쿼리어노테이션 1,2
//	@Test
//	public void QueryAnnotationTest1() {
//		List<Board> boardList = boardRepo.queryAnnotationTest1("title10");
//		System.out.println("--> QueryAnnotationTest1");
//		for (Board b : boardList)
//			System.out.println(b);
//	}
	// 쿼리어노테이션 3
//	@Test
//	public void QueryAnnotationTest2() {
//		List<Object[]> boardList = boardRepo.queryAnnotationTest2("title10");
//		System.out.println("--> QueryAnnotationTest2");
//		for (Object[] b : boardList) {
//			System.out.println(Arrays.toString(b));
//	}
//}
}
