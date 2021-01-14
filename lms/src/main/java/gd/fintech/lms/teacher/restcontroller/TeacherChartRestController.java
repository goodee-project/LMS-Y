package gd.fintech.lms.teacher.restcontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.TeacherChartService;

// 강사가 봐야할 차트를 보여주는 restcontroller

@RestController
public class TeacherChartRestController {
	// 강사 차트 service
	@Autowired private TeacherChartService teacherChartService;
	
	// 강좌에 관한 과제 제출률 출력
	// 매개변수 : RequestParam : lectureNo(강좌번호)
	// 리턴값 : 강좌 과제 제출률 뷰
	@GetMapping("/teacher/reportSubmitRateByLecture")
	public List<Map<String, Object>> reportSubmitRateByLecture(@RequestParam("lectureNo") int lectureNo) {
		return teacherChartService.getReportSubmitRateByLecture(lectureNo);
	}
	
	// 강좌의 관한 평가 문제 정답률 출력
	// 매개변수 : RequestParam : lectureNo(강좌번호)
	// 리턴값 : 평가 정답률 뷰
	@GetMapping("/teacher/testAnswerRateByLecture")
	public List<Map<String, Object>> testAnswerRateByLecture(@RequestParam("lectureNo") int lectureNo) {
		return teacherChartService.getTestAnswerRateByLecture(lectureNo);
	}
}
