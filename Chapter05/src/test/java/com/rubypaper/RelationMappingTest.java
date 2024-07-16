package com.rubypaper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistance.BoardRepository;
import com.rubypaper.persistance.MemberRepository;

@SpringBootTest
public class RelationMappingTest {
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;

	@Test
	public void testManyToOneInsert() {
		
		Member member1 = Member.builder()
				  .id("member1")
				  .password("member111")
				  .name("둘리")
				  .role("User")
				  .build();
		memberRepo.save(member1);
		Member member2 = Member.builder()
				  .id("member2")
				  .password("member222")
				  .name("도우너")
				  .role("Admin")
				  .build();
		memberRepo.save(member2);
		
		for (int i=1; i<=3; i++) {
			boardRepo.save(Board.builder()
					.member(member1)
					.title("둘리가 등록한 게시글 "+i)
					.content("둘리가 등록한 게시글내용 "+i)
					.createDate(new Date())
					.cnt(0L)
					.build()
			);
		}
	
		for (int i=1; i<=3; i++) {
			boardRepo.save(Board.builder()
					.member(member2)
					.title("도우너가 등록한 게시글 "+i)
					.content("도우너가 등록한 게시글내용 "+i)
					.createDate(new Date())
					.cnt(0L)
					.build()
			);
		}
	}
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(5L).orElseThrow();
		System.out.println("["+board.getSeq()+"번 게시글 정보]");
		System.out.println("제목 : "+board.getTitle());
		System.out.println("내용 : "+board.getContent());
		System.out.println("작성자 : "+board.getMember().getName());
		System.out.println("작성자 권한 : "+board.getMember().getRole());
	}
	@Test
	public void testTwoWayMapping() {
		Member member = memberRepo.findById("member1").get();
		System.out.println("===========");
		System.out.println(member.getName() + "가(이) 저장한 게시글 목록");
		System.out.println("===========");
		List<Board> list = member.getBoardList();
		for (Board board : list) {
			System.out.println(board.toString());
		}
	}
}