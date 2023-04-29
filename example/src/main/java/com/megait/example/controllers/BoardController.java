package com.megait.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.megait.example.beans.vo.BoardVO;
import com.megait.example.services.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	private final BoardService service;
	
	@GetMapping("list")
	public void list(Model model) {
		log.info("---------------------------");
		log.info("[BoardController] list()");
		log.info("---------------------------");
		model.addAttribute("list", service.getList());
	}
	
	@PostMapping("register")
	public RedirectView register(BoardVO board, RedirectAttributes rttr) {
		log.info("---------------------------");
		log.info("[BoardController] rigster : " + board);
		log.info("---------------------------");
		
		service.register(board);
		// 새롭게 등록된 번호를 같이 전달하기 위해
		// RedirectAttribute를 이용한다.
		rttr.addFlashAttribute("bno", board.getBno());
		
		// RedirectView를 사용하면 redirect 방식으로 전송이 가능하다.
		return new RedirectView("list");
	}
	
	@GetMapping({"get", "get2"})
	public void get(Long bno, HttpServletRequest request, Model model) {
		String reqURI = request.getRequestURI();
		String reqContextPath = request.getContextPath();
		String reqType = reqURI.substring(reqURI.indexOf(reqContextPath) + 7);
		
		log.info("---------------------------");
		log.info("[BoardController] get : " + reqURI);
		log.info("[BoardController] get : " + reqContextPath);
		log.info("[BoardController] get : " + reqType);
		log.info("[BoardController] get : " + bno);
		log.info("---------------------------");
		
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("modify")
	public RedirectView modify(BoardVO board, RedirectAttributes rttr) {
		log.info("---------------------------");
		log.info("[BoardController] modufy : " + board);
		log.info("---------------------------");
		
		if (service.modify(board)) {
			// addAttribute()는 get방식으로 QueryString에 전달
			// addFlashAttribute()는 session에 저장되어 전달
			rttr.addFlashAttribute("result", "success");
		}
		else {
			rttr.addFlashAttribute("result", "failure");
		}
		
		return new RedirectView("list");
	}
	
	@PostMapping("remove")
	// @ 변수명이 같은 경우 RequestParam을 쓸 필요는 없으나
	// 명시적으로 지정할 경우 파라미터 추가시 발생할 수 있는 예외를 막아준다.
	public RedirectView remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) throws Exception{
		log.info("---------------------------");
		log.info("[BoardController] remove : " + bno);
		log.info("---------------------------");
		
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		else {
			rttr.addFlashAttribute("result", "failure");
			throw new Exception("지울 수 없습니다.");
		}
		
		return new RedirectView("list");
	}
	
	@GetMapping("error")
	public void error() throws Exception{
		log.info("---------------------------");
		log.info("[BoardController] error()");
		log.info("---------------------------");
		throw new Exception("지울 수 없습니다.");
	} 
	
}
