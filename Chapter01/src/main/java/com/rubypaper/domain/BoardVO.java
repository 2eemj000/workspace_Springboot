package com.rubypaper.domain;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // private으로 선언된 변수들 자동으로 만들어줌
@Setter
@ToString
// @RequiredArgsConstructor
// @NoArgsConstructor

public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date createDate = new Date();
	private int cnt = 0;
}
