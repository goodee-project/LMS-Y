package gd.fintech.lms.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.account.mapper.LoginLogMapper;
import gd.fintech.lms.account.vo.LoginLog;

@Service("loginLogService")
public class LoginLogService {
	// LoginLogMapper 객체를 주입
	@Autowired private LoginLogMapper loginLogMapper;
	
	// 로그인 정보를 조회하는 메소드
	// 매개변수: 로그인 ID
	// 결과: 로그인 정보를 조회한 리스트 결과
	public List<LoginLog> getLoginLogListByAccountId(String accountId) {
		return loginLogMapper.selectLoginLogByAccountId(accountId);
	}
	
	// 로그인 정보를 입력하는 메소드
	// 매개변수: 로그인 ID, 세션 ID
	// 결과: 로그인 정보 입력한 결과
	public int createLoginLogByAccountId(LoginLog loginLog) {
		return loginLogMapper.insertLoginLogByAccountId(loginLog);
	}
	
	// 로그아웃 정보를 수정하는 메소드
	// 매개변수: 로그인한 세션 아이디
	// 결과: 로그아웃 시간을 수정한 결과
	public int modifyLogOutDateTimeByLoginId(String loginId) {
		return loginLogMapper.updateLogoutDateTimeByLoginId(loginId);
	}
}
