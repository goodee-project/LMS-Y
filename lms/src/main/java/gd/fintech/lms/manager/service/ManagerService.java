package gd.fintech.lms.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.account.mapper.AccountMapper;
import gd.fintech.lms.manager.mapper.StudentQueueMapper;
import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.mapper.SyllabusMapper;
import gd.fintech.lms.manager.mapper.TeacherQueueMapper;
import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.StudentQueue;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.TeacherQueue;
import gd.fintech.lms.manager.vo.Textbook;
import gd.fintech.lms.student.mapper.StudentMapper;
import gd.fintech.lms.student.vo.Student;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.Teacher;

// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	// 과목 정보 mapper
	@Autowired private SubjectMapper subjectMapper;
	// 교재 정보 mapper
	@Autowired private TextbookMapper textbookMapper;
	// 강의계획서 mapper
	@Autowired private SyllabusMapper syllabusMapper;
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
	
	// 운영자가 강의계획서를 서명하는 서비스 메소드
	// 매개변수: 
	// 리턴값: 
	public int signSyllabus(int syllabusNo) {
		return syllabusMapper.updateSyllabusManagerSign(syllabusNo);
	}
	
	// 운영자가 과목 정보를 입력하는 서비스 메소드
	// 매개변수:
	// 리턴값:
	public int createSubject(Subject subject) {
		return subjectMapper.insertSubject(subject);
	}
	
	// 운영자가 교재 정보를 등록하는 서비스 메소드
	// 매개변수: textbook
	// 리턴값: 
	public int createTextbook(Textbook textbook) {
		return textbookMapper.insertTextbook(textbook);
	}
	
	// 운영자가 교재 정보를 수정하는 서비스 메소드
	// 매개변수: 
	// 리턴값: 
	public int modifyTextbook(Textbook textbook) {
		return textbookMapper.updateTextbook(textbook);
	}
	
	
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
	// 계정 테이블의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수:
	// 학생 테이블에 기입할 모든 정보
	// 계정 ID
	public void approveStudentMembership(Student student, String accountId) {
		studentMapper.insertStudent(student);
		studentQueueMapper.deleteStudentQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
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
	// 계정 테이블의 상태를 활성화로 바꾸는 승인 기능
	// 매개변수:
	// 강사 테이블에 기입할 모든 정보
	// 계정 ID
	public void approveTeacherMembership(Teacher teacher, String accountId) {
		teacherMapper.insertTeacher(teacher);
		teacherQueueMapper.deleteTeacherQueue(accountId);
		accountMapper.updateAccountStateActiveByAccountId(accountId);
	}
}
