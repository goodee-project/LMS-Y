package gd.fintech.lms.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.admin.mapper.AdminMapper;
import gd.fintech.lms.admin.vo.Admin;
import gd.fintech.lms.admin.vo.ManagerQueue;

// 관리자가 하는 업무를 위한 서비스

@Service
@Transactional
public class AdminService {
	@Autowired private ManagerQueue managerQueue;
		
	// 관리자가 운영자의 회원가입을 승인하는 메소드
	// 매개변수: 운영자 아이디
	// 리턴값:
	// 운영자의 개인정보를 Manager에 저장
	// 운영자의 개인정보을 ManagerQueue에서 삭제
	public void approveManagerMembership(String accountId) {
		
	}
}
