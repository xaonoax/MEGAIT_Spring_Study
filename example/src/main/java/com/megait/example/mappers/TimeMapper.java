package com.megait.example.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service  // Spring에서 관리하는 Bean에 등록
@Mapper  // 마이배티스 연결
public interface TimeMapper {
//	@Select("select sysdate() from dual")
	public String getTime();
}
