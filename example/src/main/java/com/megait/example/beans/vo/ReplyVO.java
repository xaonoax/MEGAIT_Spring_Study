package com.megait.example.beans.vo;

import lombok.Data;

@Data
public class ReplyVO {
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replier;
	private String replydate;
	private String replyupdatedate;
	
}
