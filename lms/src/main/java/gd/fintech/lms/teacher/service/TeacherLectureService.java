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
	@Autowired
	private TeacherLectureMapper teacherLectureMapper;

	// 강사의 강좌 목록 출력 메서드
	// 매개변수: 강사 아이디
	// 리턴값:강사 아이디를 조회하여 강좌 정보 목록을 반환
	public List<Lecture> getTeacherLectureListByPage(String accountId, int currentPage) {
		// 현재 페이지 표시할 데이터 수
		int rowPerPage = 3;
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowPerPage", rowPerPage);
		map.put("beginRow", beginRow);
		map.put("accountId", accountId);
		map.put("lastPage", lastPage);
		
		
		List<Lecture> lectureList = teacherLectureMapper.selectTeacherLectureListByPage(map);
		map.put("lectureList", lectureList);
		logger.trace(lectureList + "<---lectureList");
		
		return lectureList;
	}

	// 강사 강좌별 정보 출력 메서드
	// 매개변수:강좌 번호
	// 리턴값:상세보기 페이지
	public Lecture getTeacherLectureOne(int lectureNo) {
		Lecture lecture = teacherLectureMapper.selectTeacherLectureOne(lectureNo);
		return lecture;
	}
}
