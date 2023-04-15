package com.megait.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service  // Spring에서 관리하는 Bean에 등록
@Mapper  // 마이배티스 연결
public interface TimeMapper {
	public String getTime();
}
