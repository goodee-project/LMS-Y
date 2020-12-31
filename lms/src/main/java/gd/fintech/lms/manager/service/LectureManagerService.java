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
import gd.fintech.lms.manager.vo.Classroom;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.manager.vo.Textbook;
import gd.fintech.lms.teacher.vo.Syllabus;



// /Lecture 를 관리하는 서비스 

@Service
@Transactional
public class LectureManagerService {
	// logger
    private final Logger logger = LoggerFactory.getLogger(LectureManagerService.class);
	
    // LectureManager에 대한 메퍼
	@Autowired private LectureManagerMapper lectureManagerMapper;
	

	
	
	// 강좌의 정보를 출력하는 서비스
	// 매개변수: 보여줄 데이터 개수 
	// 리턴값: 현재 페이지의 강좌 리스트
	public List<Lecture> getLectureListByPage(int currentPage, int rowPerPage){
		int beginRow = (currentPage -1)* rowPerPage;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("beginRow", beginRow);
		map.put("rowPerPage", rowPerPage);
		logger.debug("rowPerPage" +rowPerPage );
		logger.debug("beginRow "+beginRow );
		return lectureManagerMapper.selectLectureListByPage(map);
	}
	
	// 강좌에서의 강의실 리스트
	public List<Classroom> getLectureClassroomList(){
		return lectureManagerMapper.selectLectureClassroomList();
	}
	
	// 강좌에서의 교재 리스트
	public List<Textbook> getLectureTextBookList(){
		return lectureManagerMapper.selectLectureTextbookList();
	}
	
	// 강좌에서의 강의계획서 리스트
	public List<Syllabus> getLectureSyllabusList(){
		return lectureManagerMapper.selectLectureSyllabusList();
	}
	
	// 강좌에서의 과목 리스트
	public List<Subject> getLectureSubjectList(){
		return lectureManagerMapper.selectLectureSubjectList();
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
	

}
