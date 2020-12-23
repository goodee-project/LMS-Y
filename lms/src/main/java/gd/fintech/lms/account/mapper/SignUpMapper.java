package gd.fintech.lms.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;

// 회원가입 기능에 관한 매퍼

@Mapper
public interface SignUpMapper {
	// Account 테이블에 계정을 입력하는 메소드
	// 매개변수: 회원가입시 입력된 정보
	// 리턴값: 계정테이블에 입력한 행
	int insertAccount(Account account);
	
	// 계정 ID 중복 체크를 위한 메소드
	// 매개변수: 계정 ID
	// 리턴값: 계정 ID 조회 결과
	String selectAccountId(String accountId);
	
	// 계정 이메일 중복체크를 위한 메소드
	// 매개변수: 계정 이메일
	// 리턴값: 계정 이메일 조회 결과
	String selectAccountEmail(String accountEmail);
}
