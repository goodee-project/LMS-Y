package gd.fintech.lms.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.manager.mapper.StudentQueueMapper;
import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.mapper.SyllabusMapper;
import gd.fintech.lms.manager.mapper.TeacherQueueMapper;
import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.StudentQueue;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.TeacherQueue;
import gd.fintech.lms.manager.vo.Textbook;

// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	@Autowired SubjectMapper subjectMapper;
	@Autowired TextbookMapper textbookMapper;
	@Autowired SyllabusMapper syllabusMapper;
	@Autowired StudentQueueMapper studentQueueMapper;
	@Autowired TeacherQueueMapper teacherQueueMapper;
	
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
}
