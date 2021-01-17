package gd.fintech.lms.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentChartService;
import gd.fintech.lms.teacher.service.TeacherChartService;

// 계층별 권한에 따른 index 페이지로 이동하는 컨트롤러 클래스

@Controller
public class IndexController {
	// 강사 차트 서비스
	@Autowired TeacherChartService teacherChartService;
	// 학생 차트 서비스 객체 주입
	@Autowired StudentChartService studentChartService;
	
	// 학생 페이지로 이동하는 메소드
	// 매개변수: 세션(로그인 아이디), Model(통계를 위한 강좌 정보)
	// 리턴값: studentIndex(뷰이름)
	@GetMapping("/student/index")
	public String studentIndex(HttpSession session, Model model,
			@RequestParam(value="lectureNo", defaultValue = "-1") Integer lectureNo) {
		String accountId = session.getAttribute("accountId").toString();
		// 넘어오는 강의 번호가 없을 때 디폴트 강의번호 가져오는 메소드
		if(lectureNo==-1) {
			lectureNo = studentChartService.getDefaultLectureNo(accountId);
		}
		// 매개변수로 넘겨질 map 생성(아이디, 강좌번호)
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("lectureNo", lectureNo);
		model.addAttribute("attendanceList", studentChartService.getAttendanceDataByAccountId(map)); // 출결 데이터
		model.addAttribute("lectureNo", lectureNo);	// 기본 강좌 번호
		model.addAttribute("lectureList", studentChartService.getLectureCategoryByAccountId(accountId)); // 강좌 카테고리 데이터
		model.addAttribute("reportList", studentChartService.getReportScoreByAccountId(map));	// 과제 점수 정보
		model.addAttribute("sumScore", studentChartService.getReportSumScore(map));	// 부여받은 과제 합계
		model.addAttribute("totalScore", studentChartService.getReportTotalScore(map));	// 과제 총점
		return "student/studentIndex";
	}
	// 강사 페이지로 이동하는 메소드
	// 매개변수 : 
	// Model 
	// session
	// 리턴값: teacherIndex(뷰이름)
	@GetMapping("/teacher/index")
	public String teacherIndex(Model model, HttpSession session,
			@RequestParam(value="lectureNo", defaultValue = "-1") Integer lectureNo) {
		if(lectureNo==-1) {
			lectureNo = teacherChartService.getDefaultLecture(session);
		}
		model.addAttribute("lectureNo", lectureNo);
		model.addAttribute("lectureList", teacherChartService.getLectureListByAccountId(session));
		return "teacher/teacherIndex";
	}
	// 운영자 페이지로 이동하는 메소드
	// 리턴값: managerIndex(뷰이름)
	@GetMapping("/manager/index")
	public String managerIndex() {
		return "manager/managerIndex";
	}
	// 관리자 페이지로 이동하는 메소드
	// 리턴값: adminIndex(뷰이름)
	@GetMapping("/admin/index")
	public String adminIndex() {
		return "admin/adminIndex";
	}
}
