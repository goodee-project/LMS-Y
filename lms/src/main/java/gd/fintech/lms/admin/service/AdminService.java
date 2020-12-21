package gd.fintech.lms.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.mapper.AccountMapper;
import gd.fintech.lms.admin.mapper.ManagerQueueMapper;
import gd.fintech.lms.admin.vo.ManagerQueue;
import gd.fintech.lms.manager.mapper.ManagerMapper;

// 관리자가 하는 업무 Service

@Service
@Transactional
public class AdminService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 회원가입 승인대기 중인 운영자 개인정보 Mapper
	@Autowired private ManagerQueueMapper managerQueueMapper;
	// 회원가입 된 운영자의 개인정보 Mapper
	@Autowired private ManagerMapper managerMapper;
	// 계정 정보 Mapper
	@Autowired private AccountMapper accountMapper;
	
	// 회원가입 승인대기 중인 운영자의 정보 리스트를 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. rowPerPage(페이지 당 표시할 항목수)
	// 리턴값: 회원가입 승인대기 중인 운영자의 정보 리스트
	public List<ManagerQueue> getManagerQueueList(int currentPage, int rowPerPage) {
		// beginRow는 해당 페이지이다.
		int beginRow = (currentPage - 1) * rowPerPage;
		
		// Map의 객체 생성 후 값(beginRow, rowPerPage) 저장
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		List<ManagerQueue> managerQueueList = managerQueueMapper.selectManagerQueueList(map);
		return managerQueueList;
	}
	
	// 회원가입 승인대기 중인 운영자의 정보 리스트 페이징을 위해 총 항목수를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: 회원가입 승인대기 중인 운영자 총 항목수
	public int getManagerQueueCount() {
		return managerQueueMapper.selectManagerQueueCount();
	}
	
	// 회원가입 승인대기 중인 운영자의 개인정보를 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: 아이디에 해당하는 회원가입 승인대기 중인 운영자의 개인정보
	public ManagerQueue getManagerQueueDetail(String accountId) {
		ManagerQueue managerQueueDetail = managerQueueMapper.selectManagerQueueDetail(accountId);
		return managerQueueDetail;
	}
	
	// 관리자가 운영자의 회원가입을 승인하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값:
	// #1. 운영자의 개인정보를 Manager에 저장
	// #2. 운영자의 개인정보를 ManagerQueue에서 삭제
	// #3. 운영자의 계정의 활성화 여부를 활성화로 변경
	public void approveManagerMembership(String accountId) {
		logger.debug(accountId.toString());
		// 아이디에 해당하는 운영자의 개인정보를 Manager에 입력
		managerMapper.insertManagerFromQueue(accountId);
		// 아이디에 해당하는 운영자의 개인정보를 ManagerQueue에서 삭제 
		managerQueueMapper.deleteManagerQueue(accountId);
		// 아이디에 해당하는 계정의 활성화 여부를 활성화로 변경
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
	
	// 관리자가 운영자의 회원가입을 거부하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값:
	// #1. 운영자의 개인정보를 ManagerQueue에서 삭제
	// #2. 운영자의 계정의 활성화 여부를 탈퇴로 변경
	public void disapproveManagerMembership(String accountId) {
		logger.debug(accountId.toString());
		// 아이디에 해당하는 운영자의 개인정보를 ManagerQueue에서 삭제 
		managerQueueMapper.deleteManagerQueue(accountId);
		// 아이디에 해당하는 계정의 활성화 여부를 탈퇴로 변경
		accountMapper.updateAccountStateInvalidByAccountId(accountId);
	}
}
