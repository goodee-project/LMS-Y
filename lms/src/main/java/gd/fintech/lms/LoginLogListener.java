package gd.fintech.lms;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import gd.fintech.lms.account.service.LoginLogService;

// 로그인 기록에 관한 리스너
// 세션이 생기거나 세션이 사라질때 로그인,로그아웃 시간을 남김

@WebListener
public class LoginLogListener implements HttpSessionListener{
	// LoginLogService 객체 주입
	private LoginLogService loginLogService;

	// 세션이 발생되는 시점에 호출되는 메소드
	@Override
	public void sessionCreated(HttpSessionEvent se) {}
	
	// 세션이 사라지는 시점에 호출되는 메소드
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		loginLogService = (LoginLogService)BeanUtils.getBean("loginLogService");
		String loginId = se.getSession().getId();
		loginLogService.modifyLogOutDateTimeByLoginId(loginId);
	}
}
