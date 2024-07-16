package com.rubypaper.persistance;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
// T : 엔티티의 클래스 타입
// ID : 아이디로 매핑한 식별자 타입
	List<Board> findByTitle(String title);
	List<Board> findByContentContaining(String searchKeyword);
	List<Board> findByTitleContainingAndContentContaining(String title, String content);
	List<Board> findByTitleContaining(String searchKeyword, Pageable paging); // 데이터베이스 드라이버에 맞춰서 자동으로 만들어줌
	
	// ! 쿼리어노테이션은 반드시 테스트하고 사용하도록 !
//	@Query("SELECT b FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC") // 쿼리어노테이션 1 : 위치기반 파라미터 사용
//	List<Board> queryAnnotationTest1(String searchKeyword);
	
//	@Query("SELECT b FROM Board b WHERE b.title like %:searchKeyword% ORDER BY b.seq DESC") // 쿼리어노테이션 2 : 이름기반 파라미터 사용
//	List<Board> queryAnnotationTest1(String searchKeyword);
	
//	@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board b WHERE b.title like %?1% ORDER BY b.seq DESC") // 쿼리어노테이션 3 : 특정변수만 조회하기
//	List<Object[]> queryAnnotationTest2(@Param("searchKeyword")String searchKeyword); // 배열로 넘어옴, @Param("searchKeyword") 이건 이름 다를때만 적어주면 됨
}

