package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.mapper.AccountMapper;
import gd.fintech.lms.manager.mapper.StudentQueueMapper;
import gd.fintech.lms.manager.mapper.TeacherQueueMapper;
import gd.fintech.lms.manager.vo.StudentQueue;
import gd.fintech.lms.manager.vo.TeacherQueue;
import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.teacher.mapper.TeacherMapper;

@Service
@Transactional
public class QueueService {
	// debug를 하기위한 logger 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 학생 승인대기 mapper
	@Autowired private StudentQueueMapper studentQueueMapper;
	// 강사 승인대기 mapper
	@Autowired private TeacherQueueMapper teacherQueueMapper;
	// 학생 mapper
	@Autowired private StudentMapper studentMapper;
	// 강사 mapper
	@Autowired private TeacherMapper teacherMapper;
	// 계정 mapper
	@Autowired private AccountMapper accountMapper;
	
	// 학생 승인대기목록 출력
	// 매개변수 : 현재 페이지, 학생 이름
	// 리턴값 : 학생 승인대기목록 
	public Map<String, Object> getStudentQueueListByPage(int currentPage, String studentName) {
		// 페이지 당 보여줄 학생 수
		int rowPerPage = 10;
		// 전체 학생수 
		int studentQueueCount = studentQueueMapper.selectStudentQueueCount(studentName);
		// 시작하는 학생의 순번
		int beginRow = (currentPage-1)*rowPerPage;
		// 마지막 페이지
		int lastPage = studentQueueCount/rowPerPage;
		if(studentQueueCount%rowPerPage!=0) {
			lastPage += 1;
		}
		if (lastPage == 0) {
			currentPage = 0;
		}
		// 페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		// 네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("rowPerPage", rowPerPage);
		pageMap.put("beginRow", beginRow);
		pageMap.put("studentName", studentName);
		List<StudentQueue> studentQueueList = studentQueueMapper.selectStudentQueueListByPage(pageMap);
		logger.debug(studentQueueList.toString());
		
		Map<String, Object> map = new HashMap<>();
		map.put("studentQueueList", studentQueueList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	// 학생 승인대기목록 중 한 학생의 상세정보 확인
	// 매개변수 : 계정 ID
	// 리턴값 : 학생 승인대기목록의 상세정보
	public StudentQueue getStudentQueueDetail(String accountId) {
		logger.debug(accountId);
		return studentQueueMapper.selectStudentQueueDetail(accountId);
	}
	
	// 학생 승인대기목록의 데이터를 학생 테이블에 입력 후,
	// 학생 승인대기목록의 데이터를 삭제하고,
	// 계정의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수: 계정 ID
	public void approveStudentMembership(String accountId, HttpSession session) {
		logger.debug(accountId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("managerIdAccess", session.getAttribute("accountId"));
		
		studentMapper.insertStudentFromQueue(map);
		studentQueueMapper.deleteStudentQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
	
	// 승인 거절시 학생 승인대기목록에 있는 데이터를 삭제하고,
	// 계정의 상태를 거절로 바꾸는 거절 기능
	// 매개변수 : 계정 ID
	public void disapproveStudentMembership(String accountId) {
		logger.debug(accountId);
		studentQueueMapper.deleteStudentQueue(accountId);
		accountMapper.updateAccountStateRejectByAccountId(accountId);
	}
	
	// 강사 승인대기목록 출력
	// 매개변수 : 현재 페이지, 학생 이름
	// 리턴값 : 강사 승인대기목록 
	public Map<String, Object> getTeacherQueueListByPage(int currentPage, String teacherName) {
		// 페이지 당 보여줄 강사 수
		int rowPerPage = 10;
		// 전체 강사수 
		int teacherQueueCount = teacherQueueMapper.selectTeacherQueueCount(teacherName);
		// 시작하는 강사의 순번
		int beginRow = (currentPage-1)*rowPerPage;
		// 마지막 페이지
		int lastPage = teacherQueueCount/rowPerPage;
		if(teacherQueueCount%rowPerPage!=0) {
			lastPage += 1;
		}
		if (lastPage == 0) {
			currentPage = 0;
		}
		// 페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		// 네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		Map<String, Object> pageMap = new HashMap<>();
		pageMap.put("rowPerPage", rowPerPage);
		pageMap.put("beginRow", beginRow);
		pageMap.put("teacherName", teacherName);
		List<TeacherQueue> teacherQueueList = teacherQueueMapper.selectTeacherQueueListByPage(pageMap);
		logger.debug(teacherQueueList.toString());
		
		Map<String, Object> map = new HashMap<>();
		map.put("teacherQueueList", teacherQueueList);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	// 강사 승인대기목록 중 한 강사의 상세정보 확인
	// 매개변수 : 계정 ID
	// 리턴값 : 강사 승인대기목록의 상세정보
	public TeacherQueue getTeacherQueueDetail(String accountId) {
		logger.debug(accountId);
		return teacherQueueMapper.selectTeacherQueueDetail(accountId);
	}
	
	// 강사 승인대기목록의 데이터를 강사 테이블에 입력 후,
	// 강사 승인대기목록의 데이터를 삭제하고,
	// 계정의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수:
	// 강사 테이블에 기입할 모든 정보
	// 계정 ID
	public void approveTeacherMembership(String accountId, HttpSession session) {
		logger.debug(accountId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("managerIdAccess", session.getAttribute("accountId"));
		
		teacherMapper.insertTeacherFromQueue(map);
		teacherQueueMapper.deleteTeacherQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
	
	// 승인 거절시 강사 승인대기목록에 있는 데이터를 삭제하고,
	// 계정의 상태를 거절로 바꾸는 거절 기능
	// 매개변수 : 계정 ID
	public void disapproveTeacherMembership(String accountId) {
		logger.debug(accountId);
		teacherQueueMapper.deleteTeacherQueue(accountId);
		accountMapper.updateAccountStateRejectByAccountId(accountId);
	}
}
