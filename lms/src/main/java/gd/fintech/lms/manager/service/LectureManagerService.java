package gd.fintech.lms.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.manager.mapper.SubjectMapper;
import gd.fintech.lms.manager.mapper.TextbookMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.Textbook;

// /Lecture 를 관리하는 서비스 

@Service
@Transactional
public class LectureManagerService {
	// logger
    private final Logger logger = LoggerFactory.getLogger(LectureManagerService.class);
	
    // LectureManager에 대한 메퍼
	@Autowired LectureManagerMapper lectureManagerMapper;
	
	// 과목 정보 
	@Autowired SubjectMapper subjectMapper;
	
	// 교재 정보
	@Autowired TextbookMapper textbookMapper;
	
	
	// 강좌의 정보를 출력하는 서비스
	// 매개변수: 보여줄 데이터 개수 
	// 리턴값: 현재 페이지의 강좌 리스트
	public List<Lecture> getLectureListByPage(int currentPage, int rowPerPage){
		int beginRow = (currentPage -1)* rowPerPage;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		logger.debug("beginRow "+beginRow );
		map.put("rowPerPage", rowPerPage);
		logger.debug("rowPerPage" +rowPerPage );
		
		return lectureManagerMapper.selectLectureListByPage(map);
	}
	
	
	
	
	// 강좌의 총 합을 보여주는 서비스
	// 리턴값: 강좌 개수의 총 합
	public int getLectureCount(){
		return lectureManagerMapper.selectLectureCount();
		
	}
	
	
	// 강좌를 개설 하는 서비스
	// 매개변수: 강좌의 정보 
	// 리턴값: 입력 받은 정보가 들어간 행 추가 
	public int createLecture(Lecture lecture){
		return lectureManagerMapper.insertLecture(lecture);
		
	}
	
	
	// 강좌를 수정하는 서비스
	// 매개변수: 강좌의 정보 
	// 리턴값: 입력 받은 정보가 들어간 행 수정 
	public int modifyLecture(Lecture lecture) {
		return lectureManagerMapper.updateLecture(lecture);
	}
	
	
	// 강좌를 삭제하는 서비스
	// 매개변수: 강좌의 정보 
	// 리턴값: 입력받은 강좌의 번호가 들어간 행 삭제
	public int removeLecture(int lectureNo) {
		return lectureManagerMapper.deleteLecture(lectureNo);	
	}
	
	// 강좌 상세보기 서비스 
	// 매개변수: 강좌 고유번호
	// 리턴값: 강좌 고유번호에 대한 강좌 상세보기
	public Lecture managerLectureDetail(int lectureNo) {
		return lectureManagerMapper.selectLectureDetail(lectureNo);
	}
	
	public List<Subject> getSubjectList(Map<String, Integer> map){
		return  subjectMapper.selectSubjectList(map);
		
	}
	
	public List<Textbook> getTextbookList(Map<String, Integer> map) {
		return textbookMapper.selectTextbookList(map);
	}
}
