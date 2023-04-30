package com.megait.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/js/**")
		   .addResourceLocations("classpath:/static/js/")
		   .setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/css/**")
		   .addResourceLocations("classpath:/static/css/")
		   .setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/img/**")
		   .addResourceLocations("classpath:/static/img/")
		   .setCachePeriod(60 * 60 * 24 * 365);
		reg.addResourceHandler("/font/**")
		   .addResourceLocations("classpath:/static/font/")
		   .setCachePeriod(60 * 60 * 24 * 365);
	}
}
