package edu.pnu.domain;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder // builder 패턴으로 객체생성
public class MemberVO {
	private Integer id;
	private String pass;
	private String name;
	private Date regidate;
}
