package edu.pnu.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity // 데이터베이스의 테이블과 일대일로 매핑
public class Member {

	@Id
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private boolean enabled;
	
}
