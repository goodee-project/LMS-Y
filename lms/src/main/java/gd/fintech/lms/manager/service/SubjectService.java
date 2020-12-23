package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.vo.Subject;

// 과목 정보 관련 Service

@Service
@Transactional
public class SubjectService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 과목 정보 Mapper
	@Autowired private SubjectMapper subjectMapper;
	
	// 과목 목록을 페이징하여 출력하는 메소드
	// 매개변수:
	// #1. currentPage(현재 페이지)
	// #2. rowPerPage(페이지 당 표시할 항목수)
	// 리턴값: 과목 목록
	public List<Subject> getSubjectList(int currentPage, int rowPerPage) {
		// 해당 페이지에 표시한 항목
		int beginRow = (currentPage - 1) * rowPerPage;
		
		// Map의 객체 생성 후 값(beginRow, rowPerPage) 저장
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		
		List<Subject> subjectList = subjectMapper.selectSubjectList(map);
		return subjectList;
	}
	
	// 과목 목록을 페이징 하기 위해 총 항목수를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: 과목 정보 총 항목수
	public int getSubjectCount() {
		return subjectMapper.selectSubjectCount();
	}
	
	// 과목 정보를 출력하는 메소드
	// 매개변수: subjectNo(과목 고유번호)
	// 리턴값: 고유번호에 해당하는 과목 정보
	public Subject getSubjectDetail(int subjectNo) {
		Subject subjectDetail = subjectMapper.selectSubjectDetail(subjectNo);
		return subjectDetail;
	}
	
	// 운영자가 과목 정보를 입력하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: 과목 정보 입력
	public int createSubject(Subject subject) {
		logger.debug(subject.toString());
		return subjectMapper.insertSubject(subject);
	}
	
	// 운영자가 과목 정보를 수정하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: 과목 정보 수정
	public int modifySubject(Subject subject) {
		logger.debug(subject.toString());
		return subjectMapper.updateSubject(subject);
	}
}
