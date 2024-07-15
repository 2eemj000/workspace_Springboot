package com.rubypaper.domain;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@Entity
// class와 1:1로 매치시켜줌
@Builder
// Builder 쓰면 생성자 만들어줘야하니까 NoArgsConstructor,AllArgsConstructor 같이 써줘야함
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	@Id
	// primarykey로 사용하려면 : @Id 붙여줌
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 타입 Long일 때, 자동으로 작성(1씩 증가하는)되도록 하는 값 : @GeneratedValue
	private Long seq;
	private String title;
	// private String writer;
	private String content;
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date createDate; 
	private Long cnt;
	
	@ManyToOne // Many가 나자신(Board)
	@JoinColumn(name="MEMBER_ID") // member테이블의 id필드와 연결될 필드명, 이름안주면 클래스_member컬럼이름
	private Member member;
}
