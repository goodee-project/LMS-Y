package gd.fintech.lms.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.account.mapper.LoginLogMapper;
import gd.fintech.lms.account.vo.LoginLog;

@Service
public class LoginLogService {
	// LoginLogMapper 객체를 주입
	@Autowired LoginLogMapper loginLogMapper;
	
	// 로그인 정보를 조회하는 메소드
	// 매개변수: 로그인 ID
	// 결과: 로그인 정보를 조회한 리스트 결과
	public List<LoginLog> getLoginLogListByAccountId(String accountId) {
		return loginLogMapper.selectLoginLogByAccountId(accountId);
	}
	
	// 로그인 정보를 입력하는 메소드
	// 매개변수: 로그인 ID
	// 결과: 로그인 정보 입력한 결과
	public int createLoginLogByAccountId(String accountId) {
		return loginLogMapper.insertLoginLogByAccountId(accountId);
	}
	
	// 로그아웃 정보를 수정하는 메소드
	// 매개변수: 로그인 ID
	// 결과: 로그아웃 시간을 수정한 결과
	public int modifyLogOutDateTimeByAccountId(String accountId) {
		return loginLogMapper.updateLogoutDateTimeByAccountId(accountId);
	}
}
