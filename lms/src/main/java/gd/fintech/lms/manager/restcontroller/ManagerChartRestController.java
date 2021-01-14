package gd.fintech.lms.manager.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.manager.service.ManagerChartService;

// 운영자가 보는 차트 비동기 Controller

@RestController
public class ManagerChartRestController {
	// 운영자 차트 데이터 Service
	@Autowired ManagerChartService managerChartService;
	
	// 강사의 인원수와 학생의 인원수를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: teacherAndStudentCountChart(강사와 학생 인원수 데이터)
	@GetMapping("/manager/teacherAndStudentCountChart")
	public Map<String, Object> teacherAndStudentCountChart() {
		Map<String, Object> teacherAndStudentCountChart = managerChartService.getTeacherAndStudentCountChart();
		return teacherAndStudentCountChart;
	}
}
