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
import gd.fintech.lms.admin.mapper.DormantAccountMapper;
import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.student.vo.Student;
import gd.fintech.lms.teacher.vo.Teacher;

@Service
@Transactional
public class DormantAccountService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 계정 관련 Mapper
	@Autowired private AccountMapper accountMapper;
	// 휴면계정 관련 Mapper
	@Autowired private DormantAccountMapper dormantAccountMapper;
	
	// 운영자 휴면계정 목록을 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. searchType(검색조건)
	// #3. searchKeyword(검색어)
	// 리턴값: 운영자 휴면계정 목록
	public Map<String, Object> getDormantAccountListByManager(int currentPage, String searchType, String searchKeyword) {		
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		// 총 항목수
		int totalCount = dormantAccountMapper.selectDormantAccountCountByManager(searchType, searchKeyword);
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
		paramMap.put("searchType", searchType);
		paramMap.put("searchKeyword", searchKeyword);
		
		List<Manager> dormantAccountListByManager = dormantAccountMapper.selectDormantAccountListByManager(paramMap);
		returnMap.put("dormantAccountListByManager", dormantAccountListByManager);
		
		return returnMap;
	}
	
	// 강사 휴면계정 목록을 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. searchType(검색조건)
	// #3. searchKeyword(검색어)
	// 리턴값: 강사 휴면계정 목록
	public Map<String, Object> getDormantAccountListByTeacher(int currentPage, String searchType, String searchKeyword) {		
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		// 총 항목수
		int totalCount = dormantAccountMapper.selectDormantAccountCountByTeacher(searchType, searchKeyword);
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
		paramMap.put("searchType", searchType);
		paramMap.put("searchKeyword", searchKeyword);
		
		List<Teacher> dormantAccountListByTeacher = dormantAccountMapper.selectDormantAccountListByTeacher(paramMap);
		returnMap.put("dormantAccountListByTeacher", dormantAccountListByTeacher);
		
		return returnMap;
	}
	
	// 학생 휴면계정 목록을 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. searchType(검색조건)
	// #3. searchKeyword(검색어)
	// 리턴값: 학생 휴면계정 목록
	public Map<String, Object> getDormantAccountListByStudent(int currentPage, String searchType, String searchKeyword) {		
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		// 총 항목수
		int totalCount = dormantAccountMapper.selectDormantAccountCountByStudent(searchType, searchKeyword);
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
		paramMap.put("searchType", searchType);
		paramMap.put("searchKeyword", searchKeyword);
		
		List<Student> dormantAccountListByStudent = dormantAccountMapper.selectDormantAccountListByStudent(paramMap);
		returnMap.put("dormantAccountListByStudent", dormantAccountListByStudent);
		
		return returnMap;
	}
	
	// 관리자가 휴면계정을 활성화하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: 없음
	// 휴면계정의 계정 상태를 활성화로 변경
	public void updateDormantAccountState(String accountId) {
		// 정말 휴면계정인지 확인하기 위해 계정 상태를 불러오는 코드
		String accountState = dormantAccountMapper.selectAccountState(accountId);
		logger.debug(accountState);
		// 만약 계정 상태가 휴면이라면 활성화로 변경
		if(accountState.equals("휴면")) {
			accountMapper.updateAccountStateActiveByAccountId(accountId);	
		}
	}
}
