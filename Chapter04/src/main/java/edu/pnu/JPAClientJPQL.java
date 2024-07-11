package edu.pnu;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import edu.pnu.domain.Board;

public class JPAClientJPQL {
	public static void main(String[] args) {
		// emf는 어플리케이션 실행 시 딱 한번만 생성, em은 기능할때마다 만들어졌다가 사라졌다가
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		// 글 목록 조회
		try {
			String jpql = "select b from Board b where b.seq<5 order by b.seq asc";
			// native sql에서는 select *, 테이블 인덱스를 가져옴 
			// jpql은 select b(객체), Board도 entity class라서 대소문자 구분해야함
			// + b.seq는 클래스의 필드명으로 작성해야함 (만약 createdate를 cd라고 네이밍했으면 createdate라고 그대로 써야함 - 테이블명X) 
			
			TypedQuery<Board> tq = em.createQuery(jpql, Board.class);
			List<Board> list = tq.getResultList();
			for (Board b : list)
				System.out.println(b);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
