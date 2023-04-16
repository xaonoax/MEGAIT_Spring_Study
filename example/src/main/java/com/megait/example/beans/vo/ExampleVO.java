package com.megait.example.beans.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ExampleVO {
	private String name;
	private int age;
}