package edu.pnu;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.pnu.domain.Board;

public class JPAClientPersist {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction(); // 수정가능하도록 (commit, rollback)
		try {
			tx.begin();
			
			for (int i =1; i<10; i++) {
			Board board = new Board();
			board.setTitle("JPA 제목" + i);
			board.setWriter("Writer" + i);
			board.setContent("Content" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			em.persist(board); // 글등록 (save 기능)
			}
			tx.commit();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
