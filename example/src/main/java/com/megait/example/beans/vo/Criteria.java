package com.megait.example.beans.vo;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

@Component
@Data
public class Criteria {  // 게시글 검색의 기준
	private int pageNum;  // 현재 몇 번째 페이지인가.
	private int amount;  // 한 페이지에 보여줄 게시글의 갯수
	private int limit;
	private int offset;
	
	// 검색 기능을 위한 field
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);
//		pageNum = 1;
//		amount = 10;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.limit = pageNum * amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public void setParam() {
		this.limit = pageNum * amount;
		this.offset = (pageNum - 1) * amount;
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("keyword", this.keyword)
				.queryParam("type", this.type);
		return builder.toUriString();
	}
	
	public String[] getTypeArr() {
		// split("") : 모든 문자가 분리 "ABC".split("") -> "A", "B", "C"
		return type == null? new String[] {} : type.split("");
	}
}
