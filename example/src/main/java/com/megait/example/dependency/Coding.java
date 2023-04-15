package com.megait.example.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class Coding {
	
	// 필드 주입
	@Autowired
	private Computer computer;

}
