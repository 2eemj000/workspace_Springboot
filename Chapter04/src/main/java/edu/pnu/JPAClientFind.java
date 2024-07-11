package edu.pnu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

// 데이터 읽어오기
public class JPAClientFind {
	public static void main(String[] args) {
		// emf는 어플리케이션 실행 시 딱 한번만 생성, em은 기능할때마다 만들어졌다가 사라졌다가
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		
		try {
			Board searchBoard = em.find(Board.class, 1L); // 1L: long타입
			System.out.println("--->"+searchBoard);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
}
