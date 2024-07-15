package com.rubypaper;

import java.util.Date;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.rubypaper.domain.Board;
import com.rubypaper.persistance.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // autowired 안쓰고 final쓸 때
//@Component // data가 빈객체로 올라가야 실행이 됨
public class DataInit implements ApplicationRunner {
	
	private final BoardRepository boardRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for (int i=1; i<=100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
					//.writer("member1")
					.content("content"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build()
			);
		}
	
		for (int i=1; i<=100; i++) {
			boardRepo.save(Board.builder()
					.title("title"+i)
					//.writer("member2")
					.content("content"+i)
					.createDate(new Date())
					.cnt((long)(Math.random()*100))
					.build()
			);
		}
	}
}