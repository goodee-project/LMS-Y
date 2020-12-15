package gd.fintech.lms.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;

// 회원 계정 등록 정보에 관한 인터페이스

@Mapper
public interface MemberMapper {
	// Account테이블에서 계정 조회하는 메소드
	// 매개변수: 로그인폼에서 입력한 ID, PW
	// 리턴값: 등록된 계정 정보인지 여부를 조회하여 반환
	Account selectMemberById(Account account);	
}
