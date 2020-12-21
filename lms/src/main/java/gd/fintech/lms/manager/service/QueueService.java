package gd.fintech.lms.manager.service;

import java.util.List;

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
	// 리턴값 : 학생 승인대기목록 
	public List<StudentQueue> getStudentQueueList() {
		return studentQueueMapper.selectStudentQueueList();
	}
	
	// 학생 승인대기목록 중 한 학생의 상세정보 확인
	// 매개변수 : 계정 ID
	// 리턴값 : 학생 승인대기목록의 상세정보
	public StudentQueue getStudentQueueDetail(String accountId) {
		return studentQueueMapper.selectStudentQueueDetail(accountId);
	}
	
	// 학생 승인대기목록의 데이터를 학생 테이블에 입력 후,
	// 학생 승인대기목록의 데이터를 삭제하고,
	// 계정의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수: 계정 ID
	public void approveStudentMembership(String accountId) {
		studentMapper.insertStudentFromQueue(accountId);
		studentQueueMapper.deleteStudentQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
	
	// 승인 거절시 학생 승인대기목록에 있는 데이터를 삭제하고,
	// 계정의 상태를 거절로 바꾸는 거절 기능
	// 매개변수 : 계정 ID
	public void disapproveStudentMembership(String accountId) {
		studentQueueMapper.deleteStudentQueue(accountId);
		accountMapper.updateAccountStateInvalidByAccountId(accountId);
	}
	
	// 강사 승인대기목록 출력
	// 리턴값 : 강사 승인대기목록 
	public List<TeacherQueue> getTeacherQueueList() {
		return teacherQueueMapper.selectTeacherQueueList();
	}
	
	// 강사 승인대기목록 중 한 강사의 상세정보 확인
	// 매개변수 : 계정 ID
	// 리턴값 : 강사 승인대기목록의 상세정보
	public TeacherQueue getTeacherQueueDetail(String accountId) {
		return teacherQueueMapper.selectTeacherQueueDetail(accountId);
	}
	
	// 강사 승인대기목록의 데이터를 강사 테이블에 입력 후,
	// 강사 승인대기목록의 데이터를 삭제하고,
	// 계정의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수:
	// 강사 테이블에 기입할 모든 정보
	// 계정 ID
	public void approveTeacherMembership(String accountId) {
		teacherMapper.insertTeacherFromQueue(accountId);
		teacherQueueMapper.deleteTeacherQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
	
	// 승인 거절시 강사 승인대기목록에 있는 데이터를 삭제하고,
	// 계정의 상태를 거절로 바꾸는 거절 기능
	// 매개변수 : 계정 ID
	public void disapproveTeacherMembership(String accountId) {
		teacherQueueMapper.deleteTeacherQueue(accountId);
		accountMapper.updateAccountStateInvalidByAccountId(accountId);
	}
}
