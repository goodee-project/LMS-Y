package gd.fintech.lms.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Career;

// 경력 정보에 관한 매퍼 인터페이스

@Mapper
public interface CareerMapper {
	// 계정 ID별 경력을 조회하는 메소드
	// 매개변수: 계정의 ID 값
	// 리턴값: ID에 따른 경력 정보 조회 결과값
	Career selectCareerByAccountId(String accountId);	
}
