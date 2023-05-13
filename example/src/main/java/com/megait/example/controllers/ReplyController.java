package com.megait.example.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.megait.example.beans.vo.ReplyVO;
import com.megait.example.services.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController  // ViewResolver를 사용하지 않고 리턴값을 그대로 데이터로 사용. 내부 메서드는 REST방식으로만 사용 가능.
@RequiredArgsConstructor
@RequestMapping("/replies/*")
@Slf4j
public class ReplyController {

	private final ReplyService replyService;
	
	// 댓글 등록
	// 브라우저에서 JSON 타입으로 데이터를 전송하고
	// 서버에서는 댓글 처리 결과에 따라 문자열로 결과를 리턴
	// consumes : Ajax를 통해 전달받은 데이터의 타입
	// produces : Ajax의 success:function(result)의 result에 전달할 데이터의 타입
	// @ResponseBody : @Controller에서 Body를 응답하기 위해
	//					즉, viewResolver를 가지 않게 하기 위해 사용
	// 문자열 전송시 한글이 깨지지 않으려면 text/plain; charset=utf-8 사용
	@PostMapping(value="/new", consumes="application/json", produces="text/plain; charset=utf-8")
	// ResponseEntity : 서버의 상태코드, 응답 메시지 등을 받을 수 있는 타입
	// RequestBody를 적용하여 JSON 데이터를 ReplyVO 타입으로 변환하도록 저장
	public ResponseEntity<String> create(@RequestBody ReplyVO reply) 
		throws UnsupportedEncodingException {
		int insertCount = 0;
		log.info("ReplyVO : insert ----------------------- ");
		log.info(reply.toString());
		insertCount = replyService.register(reply);
		
		if(insertCount == 1) {
			return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
		
}
