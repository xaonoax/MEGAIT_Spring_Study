package com.megait.example.controllers;

import java.util.Calendar;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.megait.example.beans.vo.ExampleTaskVO;
import com.megait.example.beans.vo.ExampleVO;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ex/*")
@Slf4j
public class ExampleController {
	
	@RequestMapping(value="/example", method= {RequestMethod.GET, RequestMethod.POST})
	public void example01() {
		log.info("example get or post");
	}
	
	@RequestMapping("")
	public void example02() {
		log.info("example02 get or post");
	}
	
	@GetMapping("")
	public void example03() {
		log.info("example03 only get");
	}
	
	@GetMapping("/ex04")
	public String example04(@ModelAttribute("MyModel") ExampleVO vo) {
		log.info("---------------------------");
		log.info(vo.toString());
		log.info("---------------------------");
		vo.setName("Aon");
		vo.setAge(10);
		return "example04";
	}
	
	@GetMapping("/ex05")
	public String example05(@ModelAttribute("MyScore") ExampleTaskVO vo) {
		log.info("---------------------------");
		log.info(vo.toString());
		log.info("---------------------------");
		Random rd = new Random();
		vo.setKor(rd.nextInt(100));
		vo.setEng((int)(Math.random() * 99));
		vo.setMath((int)(Math.random() * 99));
		return "example05";
	}
	
	@GetMapping("/ex06")
	public String example06(HttpServletRequest req) {
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		ExampleVO vo = new ExampleVO();
		vo.setName(name);
		vo.setAge(age);
		log.info(vo.toString());
		return "index";
	}
	
	// getParameter없이 자동으로 데이터 매핑하는 방법
	@GetMapping("/ex07")
	public String example07(ExampleVO vo, String gender) {
		log.info(vo.toString());
		log.info(gender);
		return "index";
	}
	
	// 파라미터 이름과 매개변수 이름이 다를 때
		@GetMapping("/ex08")
		public String example08(ExampleVO vo, @RequestParam("data") String gender) {
			log.info(vo.toString());
			log.info(gender);
			return "index";
		}
		
	// 여러 다른 종류의 파라미터를 보내줘야 할 때
		@GetMapping("/ex09")
		public String example09(ExampleVO vo, @RequestParam("gender") String gender, Model model) {
			log.info(vo.toString());
			log.info(gender);
			model.addAttribute("vo", vo);
			model.addAttribute("gender", gender);
			model.addAttribute("today", "boring");
			return "example09";
		}
	
	@GetMapping("/ex10")
	public String example10() {
		log.info("-------------example10 called");
		return "example10_login";
	}
	
	@PostMapping("/ex10_login")
	public String example10_login(String id, String pw) {
		log.info("----------------------------------");
		log.info("id = " + id);
		log.info("----------------------------------");
		if (id.equals("admin")) {
			return "example10_admin";
		}
		else {
			return "example10_user";
		}
	}
	
	@GetMapping("/ex11")
	public String checkIn() {
		return "example11_checkIn";
	}
	
	@GetMapping("/ex11_getToWork")
	public String getToWork(String name) {
		log.info("---------------------- Get To Work");
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		boolean late = (hour == 9 && minute > 0 ) || (hour > 9);
		
		if (late) {
			return "example11_late";
		}
		return "example11_work";
	}
	
	@GetMapping("/ex11_leaveWork")
	public String leaveWork(@ModelAttribute("name") String name) {
		log.info("---------------------- Leave Work");
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		// 18시 00분 퇴근도 불법이라 여길 때라고 가정...^^;
		boolean early = (hour < 18) || (hour == 18 && minute == 0);
		
		// 1. input parameter에 @ModelAttribute가 있을 경우 모델이 그대로 전달됨
		
		// 2. 추가 모델이 필요한 경우 input parameter에 Model을 추가하고
		//	  addAttribute를 추가해서 데이터를 보낼 수 있음
//		model.addAttribute("name", name);
		
		if (early) {
			return "example11_early";
		}
		
		return "example11_leave";
	}

}
