package gd.fintech.lms.student.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.student.mapper.StudentAttendanceMapper;
import gd.fintech.lms.student.vo.Attendance;


@Service
@Transactional
public class StudentAttendanceService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	StudentAttendanceMapper studentAttendanceMapper;

	public Map<String,Object> getStudentAttendanceListByPage(int lectureNo, int currentPage,HttpSession session) {
		// 페이지의 데이터 갯수
		int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		
		Map<String,Object> map = new HashMap<>();
		map.put("lectureNo", lectureNo);
		map.put("accountId",session.getAttribute("accountId"));
		// 전체 페이지 갯수
		int attendanceCount = studentAttendanceMapper.attendanceCount(map);
		
		logger.debug(attendanceCount + "출석갯수");
		// 마지막 페이지
		int lastPage = attendanceCount / rowPerPage;

		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (attendanceCount % rowPerPage != 0) {
			lastPage += 1;
			logger.debug("진입");
		}

		// 전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if (lastPage == 0) {
			currentPage = 0;
		}

		// 페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		// 네비바 첫번째 페이지
		int navBeginPage = (currentPage - 1) / navPerPage * navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}

		Map<String, Object> parmMap = new HashMap<>();
		parmMap.put("rowPerPage", rowPerPage);
		parmMap.put("beginRow", beginRow);
		parmMap.put("lectureNo", lectureNo);
		parmMap.put("attendanceCount", attendanceCount);
		parmMap.put("accountId", session.getAttribute("accountId"));

		// 담아주기
		List<Attendance> attendanceList = studentAttendanceMapper.studentAttendanceListByPage(parmMap);
		logger.debug(attendanceList.toString());

		Map<String, Object> attendanceMap = new HashMap<>();
		attendanceMap.put("attendanceList", attendanceList);
		attendanceMap.put("lastPage", lastPage);
		attendanceMap.put("navPerPage", navPerPage);
		attendanceMap.put("navBeginPage", navBeginPage);
		attendanceMap.put("navLastPage", navLastPage);
		
		return attendanceMap;
	}
	
}
