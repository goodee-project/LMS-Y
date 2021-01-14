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
import gd.fintech.lms.teacher.vo.Teacher;



// /Lecture 를 관리하는 서비스 

@Service
@Transactional
public class LectureManagerService {
	// logger
    private final Logger logger = LoggerFactory.getLogger(LectureManagerService.class);
	
    // LectureManager에 대한 메퍼
	@Autowired private LectureManagerMapper lectureManagerMapper;
	

		// 강좌 리스트를 보여주는 서비스
		// 매개변수: 현재 페이지
		// 리턴값: 현재 페이지의 강좌 리스트
		public Map<String, Object> getManagerLectureListByPage(int currentPage ){
			//  페이지당 표시되는 데이터 수
			int rowPerPage = 15;
			// 현재 페이지에서 시작하는 데이터 
			int beginRow = (currentPage - 1) * rowPerPage;
			// 전체 페이지 개수
			int countLecture = lectureManagerMapper.selectLectureCount();
			// 마지막 페이지
			int lastPage = countLecture / rowPerPage;
			if (countLecture % rowPerPage != 0) {
				lastPage += 1;
			} 
			// 마지막 페이지가 0일때 현재페이지0
			if (lastPage == 0) {
				currentPage = 0;
			}
			
			// 페이지 네비에서 표시할 페이지 수 
			int navPerPage = 10;
			// 페이지 네비에서의 처음 페이지	                  
			int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
			// 페이지 네비에서의  마지막 페이지
			int navLastPage = (navBeginPage + navPerPage) - 1;
			
			// navLastPage 가 lastPage 보다 크면 navLastPage의 값은 lastPage
			if (navLastPage > lastPage) {
				navLastPage = lastPage;
			}
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("beginRow", beginRow);
			map.put("rowPerPage",rowPerPage);
			logger.debug("beginRow:"+beginRow);
			logger.debug("rowPerPage:"+rowPerPage);
			Map<String, Object> paramMap = new HashMap<String,Object>();
			List<Lecture> ManagerLectureList = lectureManagerMapper.selectLectureListByPage(map);
			paramMap.put("ManagerLectureList",ManagerLectureList);
			paramMap.put("lastPage",lastPage);
			paramMap.put("navBeginPage",navBeginPage);
			paramMap.put("navLastPage",navLastPage);
			paramMap.put("navPerPage",navPerPage);
			logger.debug("ManagerLectureList:"+ManagerLectureList);
			
			return paramMap;
		}
	
	// 강좌에서의 강의실 리스트
	public List<Classroom> getLectureClassroomList(){
		return lectureManagerMapper.selectLectureClassroomList();
	}
	
	// 강좌에서의 교재 리스트
	public List<Textbook> getLectureTextBookList(){
		return lectureManagerMapper.selectLectureTextbookList();
	}
	
	// 강좌에서의 과목 리스트
	public List<Subject> getLectureSubjectList(){
		return lectureManagerMapper.selectLectureSubjectList();
	}
	
	// 강좌에서 강사 리스트
	public List<Teacher> getLectureTeacherList(){
		return lectureManagerMapper.selectLectureTeacherList();
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
	public Lecture getManagerLectureDetail(int lectureNo) {
		return lectureManagerMapper.selectLectureDetail(lectureNo);
	}
}
