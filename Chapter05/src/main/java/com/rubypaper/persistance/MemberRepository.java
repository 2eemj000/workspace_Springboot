package com.rubypaper.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rubypaper.domain.Member;

public interface MemberRepository extends JpaRepository<Member,String> {

}
