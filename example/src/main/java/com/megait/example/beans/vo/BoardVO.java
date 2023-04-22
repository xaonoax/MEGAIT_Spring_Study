package com.megait.example.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updatedate;
}
