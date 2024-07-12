package com.rubypaper.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
// T : 엔티티의 클래스 타입
// ID : 식별자 타입

}
