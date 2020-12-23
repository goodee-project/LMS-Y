package gd.fintech.lms.account.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.admin.mapper.AdminMapper;
import gd.fintech.lms.admin.vo.Admin;
import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.Student;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.Teacher;
import gd.fintech.lms.account.mapper.AccountMapper;

// 회원 등록 여부를 확인하기 위한 서비스 클래스

@Service
@Transactional
public class AccountService {
	// AccountMapper 객체 주입
	@Autowired private AccountMapper accountMapper;
	// StudentMapper 객체 주입
	@Autowired private StudentMapper studentMapper;
	// TeacherMapper 객체 주입
	@Autowired private TeacherMapper teacherMapper;
	// ManagerMapper 객체 주입
	@Autowired private ManagerMapper managerMapper;
	// AdminMapper 객체 주입
	@Autowired private AdminMapper adminMapper;
	
	
	// 계정 등록 여부를 조회한 결과를 가져오는 메소드(로그인을 위한 메소드)
	// 매개변수: 로그인뷰에서 입력하여 넘어온 계정 ID, PW
	// 리턴값: 계정 조회 값이 없으면 null 반환, 있으면 조회된 계정 정보(ID, Level)를 반환
	public Account getMemberById(Account account) {	
		// 계정 조회 값을 Account VO 객체에 담기
		Account memberCk = accountMapper.selectMemberById(account);	
		if(memberCk != null) {
			return memberCk;
		}
		return null;
	}
	
	// 학생 자신에 대한 정보를 상세조회하여 가져오는 메소드
	// 매개변수: 학생의 계정 ID
	// 리턴값: 해당 학생 ID의 모든 내용 조회한 리턴값
	public Student getStudentOne(String accountId){
		return studentMapper.selectStudentOne(accountId);
	}
	
	// 학생 자신의 정보 수정 액션 메소드
	// 매개변수: 학생개인정보 수정폼에서 변경하여 입력된 학생 정보
	// 리턴값: 학생 정보 수정된 행
	public int modifyStudentAction(Student student) {
		return studentMapper.updateStudent(student);
	}
	
	// 강사 자신에 대한 정보를 상세정보하여 가져오는 메소드
	// 매개변수: 강사의 계정 ID
	// 리턴값: 해당 강사 ID의 모든 내용 조회한 리턴값
	public Teacher getTeacherOne(String accountId){
		Teacher teacher = teacherMapper.selectTeacherOne(accountId);
		return teacher;
	}
	
	// 강사 아이디를 조회아여 정보를 수정하게 해주는 액션 메소드
	// 매개변수: 강사의 계정 ID
	// 리턴값: 강사 정보 수정된 행
	public int modifyTeacherAction(Teacher teacher) {
		return teacherMapper.updateTeacherInfo(teacher);
	}
	
	// 운영자 자신의 정보를 상세조회 메소드
	// 매개변수:운영자 계정 ID
	// 리턴값:
	public Manager getManagerOne(String accountId) {
		return managerMapper.selectManagerOne(accountId);
	}
	
	// 운영자 자신의 정보를 수정하는 액션 메소드
	// 매개변수: 운영자의 계정 ID
	// 리턴값: 운영자의 정보를 수정한 행
	public int modifyManagerAction(Manager manager) {
		return managerMapper.updateManager(manager);
	}
	
	// 관리자의 정보를 조회하는 메소드
	// 매개변수: 관리자의 계정 ID
	// 리턴값: 관리자의 정보를 조회한 리턴값
	public Admin getAdminOne(String accountId) {
		return adminMapper.selectAdmin(accountId);
	}
}
