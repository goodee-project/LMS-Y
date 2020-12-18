package gd.fintech.lms.account.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.Account;

// 회원 계정 등록 정보에 관한 인터페이스(로그인 기능)
// Account(계정) 테이블에서 데이터를 삭제하면 안 되는 것으로 규정(상태만 변경)

@Mapper
public interface AccountMapper {
	// Account 테이블에서 계정 조회하는 메소드
	// 매개변수: 로그인폼에서 입력한 ID, PW
	// 리턴값: 등록된 계정 정보인지 여부를 조회하여 반환
	Account selectMemberById(Account account);
	
	// Account 테이블에 등록된 모든 계정을 조회하는 메소드
	// 매개변수: 페이징을 위한 시작페이지, 한페이지에 출력되는 행의 수(Integer 형으로 받음)
	// 리턴값: 모든 계정을 조회한 결과리스트
	List<Account> selectMemberAll(Map<String, Integer> map);
	
	// Account 테이블에서 계정 상태를 활성화로 수정하는 메소드
	// 매개변수: 계정 ID
	// 리턴값: 계정상태를 활성화로 수정한 행
	int updateAccountStateActiveByAccountId(String accountId);
	
	// Account 테이블에서 계정 상태를 탈퇴로 수정하는 메소드
	// 매개변수: 계정 ID
	// 리턴값: 계정상태를 탈퇴로 수정한 행
	int updateAccountStateInvalidByAccountId(String accountId);
}
