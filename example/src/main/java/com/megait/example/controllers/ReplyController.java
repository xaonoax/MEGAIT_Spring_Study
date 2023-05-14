package com.megait.example.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megait.example.beans.vo.Criteria;
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
	
	// 게시글 댓글 전체 조회
	@GetMapping("pages/{bno}/{page}")
	public List<ReplyVO> getList(@PathVariable("bno") Long bno, @PathVariable("page") int page) {
		log.info("getList -----------------------------------> ");
		Criteria cri = new Criteria(page, 10);
		log.info(cri.toString());
		
		return replyService.getList(cri, bno);
	}
	
	// 게시글 댓글 조회
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable("rno") Long rno) {
		log.info("get ---------------------------------------> " + rno);
		
		return replyService.get(rno);
	}
	
	// 댓글 수정
	// PUT : 자원의 전체 수정, 자원의 모든 필드를 전손해야 함, 일부만 전송하면 오류
	// PATCH : 자원의 일부 수정, 수정할 필드만 전송
	// PATCH가 PUT을 포함하므로 전체를 전달받아 수정하거나, 부분만 수정하거나 모두 PATCH가 유리함
	@RequestMapping(method= {RequestMethod.PATCH},
			value= {"/{rno}", "/{rno}/{replier}"},
			consumes="application/json",
			produces="text/plain; charset=utf-8")
	// 외부에서 데이터가 전달될 때 일부분만 전달될 경우를 위해
	// {"/{rno}", "/{rno}/{replier}"}와 같이 구분한다.
	// 원래 JSON 데이터 중 replier가 전달되지 않았을 경우 검증 단계
	// (Validate, Validated, Valid)를 거쳐야 한다.
	// => 차라리 POST로 데이터를 받는게 나음
	// null인지 아닌지 검사하기 위해 객체 안의 필드를 매개변수로 따로 설정하여
	// null도 가능하도록 설정(required=false)
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO replyVO,
			@PathVariable(value = "replier", required = false) String replier,
			@PathVariable("rno") Long rno)
	throws UnsupportedEncodingException {
		replyVO.setRno(rno);
		
		int replyCount = 0;
		log.info("modify ------------------------------------> " + rno);
		log.info("modify : " + replyVO);
		if(replyVO.getReplier() == null) {
			replyVO.setReplier(Optional.ofNullable(replier).orElse("anonymous"));
		}
		replyCount = replyService.modify(replyVO);
		
		if(replyCount == 1) {
			return new ResponseEntity<>(new String("댓글 수정 성공".getBytes(), "UTF-8"), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 댓글 삭제
	@DeleteMapping(value="{rno}", produces="text/plain; charset=utf-8")
	public String remove(@PathVariable("rno") Long rno) {
		log.info("remove ------------------------------------> " + rno);
		
		return replyService.remove(rno) == 1 ? "댓글 삭제 성공" : "댓글 삭제 실패";
	}
	
}
