package gd.fintech.lms.student.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.student.service.StudentChartService;

// 학생 통계 데이터를 위한 비동기 컨트롤러

@RestController
public class StudentChartRestController {
	// StudentChartService 객체 주입
	@Autowired private StudentChartService studentChartService;
	
	// 학생 계정의 출결 상태 리스트를 가져오는 메소드
	// 매개변수: 로그인된 계정
	// 리턴값: 출결 상태 리스트
	@GetMapping("/student/attendanceChart")
	public Map<String, Object> attendanceDataByAccountId(HttpSession session, 
			@RequestParam(value = "lectureNo", required = true) int lectureNo) {
		// 로그인 된 계정 받아오기
		String accountId = session.getAttribute("accountId").toString();
		// 매개변수로 넘겨질 map 생성(아이디, 강좌번호)
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("lectureNo", lectureNo);
		return studentChartService.getAttendanceDataByAccountId(map);
	}
	
	// 학생의 과제 성적을 가져오는 메소드
	// 매개변수: 로그인된 계정
	// 리턴값: 학생의 과제명과 학생의 과제 점수, 과제 총점
	@GetMapping("/student/reportChart")
	public List<Map<String, Object>> reportScoreByAccountId(HttpSession session, 
			@RequestParam(value = "lectureNo", required = true) int lectureNo) {
		// 로그인 된 계정 받아오기
		String accountId = session.getAttribute("accountId").toString();
		// 매개변수로 넘겨질 map 생성(아이디, 강좌번호)
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("lectureNo", lectureNo);
		return studentChartService.getReportScoreByAccountId(map);
	}
}
