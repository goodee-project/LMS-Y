package gd.fintech.lms.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.account.vo.LoginLog;

// 로그인 정보에 관한 매퍼 인터페이스

@Mapper
public interface LoginLogMapper {
	// 로그인 정보를 조회하는 메소드
	// 매개변수: 로그인 계정 ID
	// 리턴값: 로그인 정보 리스트
	List<LoginLog> selectLoginLogByAccountId(String accountId);
	
	// 로그인 정보(계정아이디, 로그인시간)를 입력하는 메소드
	// 매개변수: 로그인한 계정 ID
	// 리턴값: 로그인 정보 입력된 행
	int insertLoginLogByAccountId(String accountId);
	
	// 로그아웃 정보(로그아웃시간)를 수정하는 메소드
	// 매개변수: 로그인한 계정 ID
	// 리턴값: 로그아웃시간을 수정한 행
	int updateLogoutDateTimeByAccountId(String accountId);
}
