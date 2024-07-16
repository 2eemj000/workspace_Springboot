package com.rubypaper.domain;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Builder
//Builder 쓰면 생성자 만들어줘야하니까 NoArgsConstructor,AllArgsConstructor 같이 써줘야함
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	@Id
	@Column(name="MEMBER_ID")
	private String id;
	private String password;
	private String name;
	private String role;
	
	@JsonIgnore 
	@ToString.Exclude // 양방향매칭에서 순환루프 끊어주기 위해서
	@Builder.Default
	@OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // mappedBy =는 Board안의 어떤 필드와 연결되어야하는지
	private List<Board> boardList = new ArrayList<Board>();
}
