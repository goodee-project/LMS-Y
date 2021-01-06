package gd.fintech.lms.account.service;


import java.util.Map;

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

@Service("AccountService")
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
	
	// 로그인시 사용자의 이름을 조회하는 메소드
	// 매개변수: 로그인뷰에서 입력하여 넘어온 계정 ID, PW
	// 리턴값: 조회된 계정 이름을 반환
	public String getAccountNameById(Account account) {
		// 이름 조회 값을 변수에 담기
		String accountName = accountMapper.selectAccountNameById(account);
		if(accountName != null) {
			return accountName;
		}
		return null;
	}
	
	// 사용자의 아이디를 찾아주는 메소드
	// 매개변수: 이메일
	// 리턴값: 이메일로 조회된 아이디
	public String getAccountIdByEmail(String accountEmail) {
		return accountMapper.selectAccountIdByEmail(accountEmail);
	}
	
	// 임시 비밀번호를 생성하는 메소드
	// 리턴값: 임시비밀번호
	public String getNewPw() {
		char[] charSet = {'0','1','2','3','4','5','6','7','8','9',
						'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
						'O','P','Q','R','S','T','U','V','W','X','Y','Z','!','@','#','$','*'};
		StringBuffer newKey = new StringBuffer();	// 임시번호를 저장하는 스트링버퍼
		for(int i=0; i<10; i++) {
			int idx = (int) (charSet.length * Math.random());	// 랜덤 인덱스
			newKey.append(charSet[idx]);
		}
		return newKey.toString();
	}
	
	// DB에 있는 비밀번호 변경해주는 메소드
	// 매개변수: 이메일, 비밀번호
	// 리턴값: 변경되는 행
	public int modifyAcccountPw(Map<String, Object> map) {
		return accountMapper.updateAccountPasswordByEmail(map);
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
