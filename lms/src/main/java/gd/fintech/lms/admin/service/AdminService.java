package gd.fintech.lms.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.admin.mapper.ManagerQueueMapper;
import gd.fintech.lms.manager.mapper.ManagerMapper;

// 관리자가 하는 업무를 위한 서비스

@Service
@Transactional
public class AdminService {
	@Autowired private ManagerQueueMapper managerQueueMapper;
//	@Autowired private ManagerMapper managerMapper;
//	@Autowired private AccountMapper accountMapper;
		
	// 관리자가 운영자의 회원가입을 승인하는 서비스 메소드
	// 매개변수: 운영자 아이디
	// 운영자의 개인정보를 Manager에 저장
	// 운영자의 개인정보을 ManagerQueue에서 삭제
	// 운영자의 계정의 활성화 여부를 활성화로 변경
	public void approveManagerMembership(String accountId) {
		// 아이디에 해당하는 운영자의 개인정보를 Manager에 입력
//		managerMapper.insertManager(accountId);
		// 아이디에 해당하는 운영자의 개인정보를 ManagerQueue에서 삭제 
		managerQueueMapper.deleteManagerQueue(accountId);
		// 아이디에 해당하는 계정의 활성화 여부를 활성화로 변경
//		AccountMapper.updateAccountState(accountId)
	}
}
