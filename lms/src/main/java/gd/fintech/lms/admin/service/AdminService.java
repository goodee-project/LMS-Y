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
	
	// 회원가입 승인대기 중인 운영자 목록을 페이징하여 출력하는 메소드
	// 매개변수: currentPage(현재 페이지)
	// 리턴값: 회원가입 승인대기 중인 운영자의 목록
	public Map<String, Object> getManagerQueueList(int currentPage) {
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		// 총 항목수
		int totalCount = managerQueueMapper.selectManagerQueueCount();
		// 마지막 페이지
		int lastPage = totalCount / rowPerPage;
		// 페이지 네비게이션 바에 표시할 페이지 수
		int pageNaviSize = 10;
		// 페이지 네비게이션 바의 첫번째 값
		int pageNaviBegin = (currentPage - 1) / pageNaviSize * pageNaviSize + 1;
		// 페이지 네비게이션 바의 마지막 값
		int pageNaviEnd = (pageNaviBegin + pageNaviSize) - 1;
		
		// 한 페이지에 보여줄 항목수 미만의 항목이 남을 경우 마지막 페이지를 한 페이지 추가
		if (totalCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		// 만약 마지막 페이지가 0이라면 현재 페이지도 0이 됨
		if (lastPage == 0) {
			currentPage = 0;
		}
		
		// 만약 네비게이션 바의 마지막 값이 마지막 페이지보다 크다면 네비게이션 바의 마지막 값은 마지막 페이지가 됨
		if (pageNaviEnd > lastPage) {
			pageNaviEnd = lastPage;
		}
		
		// 리턴값 저장
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("lastPage", lastPage);
		returnMap.put("pageNaviSize", pageNaviSize);
		returnMap.put("pageNaviBegin", pageNaviBegin);
		returnMap.put("pageNaviEnd", pageNaviEnd);
		
		// 파라미터값 저장
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("beginRow", beginRow);
		
		List<ManagerQueue> managerQueueList = managerQueueMapper.selectManagerQueueList(paramMap);
		returnMap.put("managerQueueList", managerQueueList);
		
		return returnMap;
	}
	
	// 회원가입 승인대기 중인 운영자의 개인정보를 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: 아이디에 해당하는 회원가입 승인대기 중인 운영자의 개인정보
	public ManagerQueue getManagerQueueDetail(String accountId) {
		ManagerQueue managerQueueDetail = managerQueueMapper.selectManagerQueueDetail(accountId);
		logger.debug(managerQueueDetail.toString());
		return managerQueueDetail;
	}
	
	// 관리자가 운영자의 회원가입을 승인하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: 없음
	// 운영자의 개인정보를 Manager에 저장
	// 운영자의 개인정보를 ManagerQueue에서 삭제
	// 운영자의 계정의 활성화 여부를 활성화로 변경
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
	// 리턴값: 없음
	// 운영자의 개인정보를 ManagerQueue에서 삭제
	// 운영자의 계정의 활성화 여부를 탈퇴로 변경
	public void disapproveManagerMembership(String accountId) {
		logger.debug(accountId.toString());
		// 아이디에 해당하는 운영자의 개인정보를 ManagerQueue에서 삭제 
		managerQueueMapper.deleteManagerQueue(accountId);
		// 아이디에 해당하는 계정의 활성화 여부를 탈퇴로 변경
		accountMapper.updateAccountStateInvalidByAccountId(accountId);
	}
}
