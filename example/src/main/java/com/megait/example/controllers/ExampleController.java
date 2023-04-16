package com.megait.example.controllers;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

}
