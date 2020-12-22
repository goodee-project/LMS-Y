package gd.fintech.lms.account.restmapper;

import org.apache.ibatis.annotations.Mapper;

// 계정 ID 중복체크 관련된 인터페이스

@Mapper
public interface AccountRestMapper {
	// 계정 ID 중복 체크를 위한 메소드
	// 매개변수: 계정 ID
	// 리턴값: 계정 ID 조회 결과
	String selectAccountId(String accountId);
}
