package gd.fintech.lms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import gd.fintech.lms.account.service.AccountService;

// 휴면 계정을 관리하기 위한 스케줄 기능의 클래스

@Component
public class AccountScheduler {
	// AccountService 객체 주입
	@Autowired AccountService accountService;
	
	Logger logger =  LoggerFactory.getLogger(AccountScheduler.class);
	
	// 매달 1일 00시에 휴면계정 전환 실행 되로록 cron 설정(초,분,시,일,월,요일)
	@Scheduled(cron="0 0 0 1 * *")
	public void dormantAccount() {
		// 서비스 메서드 호출
		accountService.modifyDormantAccountId();
		logger.trace("휴면 계정 전환 수행");
    }
}
