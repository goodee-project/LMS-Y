package gd.fintech.lms.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.fintech.lms.manager.mapper.ManagerMapper;
import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.Manager;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.Textbook;
import gd.fintech.lms.teacher.mapper.SyllabusMapper;

// 운영자가 하는 업무를 위한 서비스

@Service
public class ManagerService {
	// 과목 정보 mapper
	@Autowired private SubjectMapper subjectMapper;
	// 교재 정보 mapper
	@Autowired private TextbookMapper textbookMapper;
	// 강의계획서 mapper
	@Autowired private SyllabusMapper syllabusMapper;
	// 운영자정보 mapper 
	@Autowired private ManagerMapper managerMapper;
	
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
	
	
	// 운영자의 개인 정보를 볼수있는 서비스 메소드 
	// 매개변수: 운영자의 계정 ID
	// 리턴값: 운영자의 상세 정보 
	public Manager getManagerDetail(String accountId) {
		return managerMapper.selectManagerOne(accountId);
	}
	
	// 운영자의 개인 정보를 수정하는 서비스 메소드 
	// 매개변수: 운영자 정보
	// 리턴값: 운영자의 수정된 정보 
	public int modifyManager(Manager manager) {
		return managerMapper.updateManager(manager);
	}
}
