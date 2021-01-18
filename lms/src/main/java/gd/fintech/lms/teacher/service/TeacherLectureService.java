package gd.fintech.lms.teacher.service;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.mapper.TeacherLectureMapper;

//강사의강좌 정보 목록 및 상세보기 서비스

@Service
@Transactional
public class TeacherLectureService {
	// Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// TeacherLectureMapper 객체 주입
	@Autowired private TeacherLectureMapper teacherLectureMapper;

	// 강사의 강좌 목록 출력 메서드
	// 매개변수: 강사 아이디
	// 리턴값:강사 아이디를 조회하여 강좌 정보 목록을 반환
	public Map<String, Object> getTeacherLectureListByPage(String accountId, int currentPage) {
		// 현재 페이지 표시할 데이터 수
		int rowPerPage = 10;
		// 시작 페이지
		int beginRow = (currentPage - 1) * rowPerPage;
		// 전체 페이지 개수
		int lectureCount = teacherLectureMapper.selectTeacherLectureCount(accountId);
		// 마지막 페이지
		int lastPage = lectureCount / rowPerPage;
		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (lectureCount % rowPerPage != 0) {
			lastPage += 1;
		}
		// 전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if (lastPage == 0) {
			currentPage = 0;
		}
		// 페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		// 네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		Map<String, Object> pageMap = new HashMap<String, Object>();
		//현재 페이지 표시할 데이터 수
		pageMap.put("rowPerPage", rowPerPage);
		//시작 페이지
		pageMap.put("beginRow", beginRow);
		//강사 아이디
		pageMap.put("accountId", accountId);
		pageMap.put("currentPage", currentPage);
		List<Lecture> teacherLecture = teacherLectureMapper.selectTeacherLectureListByPage(pageMap);
		logger.trace(teacherLecture + "<---lectureList");
		Map<String, Object> map = new HashMap<>();
		//마지막 페이지
		map.put("lastPage", lastPage);
		//강좌목록
		map.put("teacherLecture", teacherLecture);
		map.put("navPerPage", navPerPage);
		map.put("navBeginPage", navBeginPage);
		map.put("navLastPage", navLastPage);
		//Logger 디버깅
		
		return map;
	}

	// 강사 강좌별 정보 출력 메서드
	// 매개변수:강좌 번호
	// 리턴값:상세보기 페이지
	public Lecture getTeacherLectureOne(int lectureNo) {
		Lecture lecture = teacherLectureMapper.selectTeacherLectureOne(lectureNo);
		return lecture;
	}
	
	//강좌 페이징 총 개수
	public int getTeacherLectureCount(String accountId) {
		return teacherLectureMapper.selectTeacherLectureCount(accountId);
	}
}
