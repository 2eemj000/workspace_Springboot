package edu.pnu.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.pnu.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}

// MemberRepository 인터페이스는 JpaRepository<Member, String>를 확장
// -> Member 엔티티와 관련된 데이터베이스 작업을 정의하는 규약(Contract)을 명시