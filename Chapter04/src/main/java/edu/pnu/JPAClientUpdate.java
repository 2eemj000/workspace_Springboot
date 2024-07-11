package edu.pnu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientUpdate {
	public static void main(String[] args) {
		// emf는 어플리케이션 실행 시 딱 한번만 생성, em은 기능할때마다 만들어졌다가 사라졌다가
		// EntityManager 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		// Transaction 생성
		EntityTransaction tx = em.getTransaction();
		try {
			// Transaction 시작
			tx.begin();
			
			// 수정할 게시글 조회
			Board searchBoard = em.find(Board.class, 1L); // 1L: long타입
			System.out.println("--->"+searchBoard);
			
			searchBoard.setTitle("수정된 타이틀");
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
